package me.bristermitten.mittenmines.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import me.bristermitten.mittenmines.block.ConstantBlockPattern
import me.bristermitten.mittenmines.compat.BlockDataFactory
import me.bristermitten.mittenmines.compat.BlockPlacer
import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.entity.*
import me.bristermitten.mittenmines.lang.LangConfig
import me.bristermitten.mittenmines.lang.LangElement
import me.bristermitten.mittenmines.lang.LangService
import me.bristermitten.mittenmines.player.MinesPlayer
import me.bristermitten.mittenmines.player.MinesPlayerStorage
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject
import kotlin.reflect.KMutableProperty1

@CommandAlias(COMMAND_NAMES)
class MinesCommand @Inject constructor(
    private val regionSelection: RegionSelection,
    private val blockPlacer: BlockPlacer,
    private val blockDataFactory: BlockDataFactory,
    private val minesPlayerStorage: MinesPlayerStorage,
    private val langService: LangService,
) : BaseCommand() {

    @Subcommand("create")
    fun createMine(player: Player) {
        val selection = regionSelection.getSelection(player) ?: run {
            player.sendMessage("You need to select a region to be the mine")
            return
        }

        val mine =
            Mine(UUID.randomUUID(), ServerOwner, null, selection, selection, player.location.toAngledWorldPoint())
        blockPlacer.setRegion(ConstantBlockPattern(blockDataFactory.createBlockData(Material.COAL_ORE)), mine.region)
        player.teleport(mine.spawnLocation.toLocation())
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
        p: KMutableProperty1<MinesPlayer, WorldPoint?>,
        other: KMutableProperty1<MinesPlayer, WorldPoint?>,
        otherResetMessage: (LangConfig) -> LangElement,
        normalMessage: (LangConfig) -> LangElement,
    ) {
        val minesPlayer = minesPlayerStorage[player]
        val newPos = player.location.toWorldPoint()
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
