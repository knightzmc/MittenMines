package me.bristermitten.mittenmines.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Subcommand
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.bristermitten.mittenmines.MittenMines
import me.bristermitten.mittenmines.block.RandomBlockPattern
import me.bristermitten.mittenmines.compat.BlockDataFactory
import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.entity.WorldBlockPoint
import me.bristermitten.mittenmines.entity.toAngledWorldPoint
import me.bristermitten.mittenmines.entity.toWorldBlockPoint
import me.bristermitten.mittenmines.lang.LangConfig
import me.bristermitten.mittenmines.lang.LangElement
import me.bristermitten.mittenmines.lang.LangService
import me.bristermitten.mittenmines.menu.BlocksMenu
import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.mine.MineManager
import me.bristermitten.mittenmines.mine.ServerOwner
import me.bristermitten.mittenmines.mine.storage.MineStorage
import me.bristermitten.mittenmines.player.MinesPlayer
import me.bristermitten.mittenmines.player.MinesPlayerStorage
import me.bristermitten.mittenmines.trait.HasPlugin
import me.bristermitten.mittenmines.util.Fail
import me.bristermitten.mittenmines.util.Success
import me.bristermitten.mittenmines.util.async
import me.bristermitten.mittenmines.util.syncDispatcher
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject
import kotlin.reflect.KMutableProperty1

@CommandAlias(COMMAND_NAMES)
class MinesCommand @Inject constructor(
    private val regionSelection: RegionSelection,
    private val blockDataFactory: BlockDataFactory,
    private val minesPlayerStorage: MinesPlayerStorage,
    private val langService: LangService,
    private val mineStorage: MineStorage,
    override val plugin: MittenMines,
    private val mineManager: MineManager,
    private val blocksMenu: BlocksMenu,
) : BaseCommand(), HasPlugin {

    @Subcommand("create")
    @CommandPermission("mittenmines.create")
    fun createMine(player: Player, name: String) {
        val selection = regionSelection.getSelection(player) ?: run {
            player.sendMessage("You need to select a region to be the mine")
            return
        }

        val pattern = RandomBlockPattern(mapOf(
            blockDataFactory.createBlockData(Material.COAL_ORE) to 1,
            blockDataFactory.createBlockData(Material.IRON_ORE) to 2
        ))
        val mine = Mine(UUID.randomUUID(),
            ServerOwner,
            name,
            selection,
            selection,
            player.location.toAngledWorldPoint(),
            pattern)

        mineManager.fill(mine)
        player.teleport(mine.spawnLocation.toLocation())
        async.launch {
            mineStorage.save(mine)
        }
    }

    @Subcommand("reset")
    @CommandPermission("mittenmines.reset")
    fun reset(sender: CommandSender, mine: Mine) {
        mineManager.fill(mine)
    }


    @Subcommand("menu")
    @CommandPermission("mittenmines.menu")
    fun menu(sender: Player, mine: Mine) {
        blocksMenu.create(mine).open(sender)
    }

    @Subcommand("delete")
    @CommandPermission("mittenmines.delete")
    fun delete(sender: CommandSender, mine: Mine) {
        async.launch {
            mineManager.delete(mine)
            withContext(syncDispatcher) {
                langService.send(sender, mapOf("{mine}" to (mine.name ?: mine.id))) { it.commands.mineDeleted }
            }
        }
    }

    @Subcommand("rename")
    @CommandPermission("mittenmines.rename")
    fun rename(sender: CommandSender, mine: Mine, newName: String) {
        val oldName = mine.name ?: "[unnamed]"
        when (val result = mineManager.rename(mine, newName)) {
            is Fail -> result.exception.report(langService, sender)
            is Success -> langService.send(
                sender,
                mapOf("{old-name}" to oldName, "{new-name}" to newName)
            ) { langConfig -> langConfig.commands.mineRenamed }
        }
    }


    @Subcommand("list")
    fun listMines(sender: CommandSender) {
        mineStorage.getAll().forEach {
            sender.sendMessage("${it.name} - ${it.owner} ${it.spawnLocation}")
        }
    }

    @Subcommand("pos1|p1")
    fun pos1(player: Player) {
        return setPos(player,
            MinesPlayer::selection1,
            MinesPlayer::selection2,
            { it.commands.pos2Reset },
            { it.commands.pos1Set })
    }

    private fun setPos(
        player: Player,
        p: KMutableProperty1<MinesPlayer, WorldBlockPoint?>,
        other: KMutableProperty1<MinesPlayer, WorldBlockPoint?>,
        otherResetMessage: (LangConfig) -> LangElement,
        normalMessage: (LangConfig) -> LangElement,
    ) {
        val minesPlayer = minesPlayerStorage[player]
        val newPos = player.location.toWorldBlockPoint()
        p.set(minesPlayer, newPos)
        val otherPoint = other.get(minesPlayer)
        if (otherPoint != null && otherPoint.world != newPos.world) {
            other.set(minesPlayer, null)
            langService.send(player, otherResetMessage)
        }
        langService.send(player, mapOf("{x}" to newPos.x, "{y}" to newPos.y, "{z}" to newPos.z), normalMessage)
    }

    @Subcommand("pos2|p2")
    fun pos2(player: Player) {
        return setPos(player,
            MinesPlayer::selection2,
            MinesPlayer::selection1,
            { it.commands.pos1Reset },
            { it.commands.pos2Set })
    }
}
