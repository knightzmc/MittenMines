package me.bristermitten.mittenmines.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import me.bristermitten.mittenmines.block.ConstantBlockPattern
import me.bristermitten.mittenmines.compat.BlockDataFactory
import me.bristermitten.mittenmines.compat.BlockPlacer
import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.entity.Mine
import me.bristermitten.mittenmines.entity.ServerOwner
import me.bristermitten.mittenmines.entity.toAngledWorldPoint
import me.bristermitten.mittenmines.entity.toWorldPoint
import me.bristermitten.mittenmines.lang.LangService
import me.bristermitten.mittenmines.player.MinesPlayerStorage
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject

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
        val minesPlayer = minesPlayerStorage[player]
        val newPos = player.location.toWorldPoint()
        minesPlayer.selection1 = newPos
        if (minesPlayer.selection2 != null && minesPlayer.selection2?.world != newPos.world) {
            minesPlayer.selection2 = null
            langService.send(player) { it.commands.pos2Reset }
        }
        langService.send(player, mapOf("{x}" to newPos.x, "{y}" to newPos.y, "{z}" to newPos.z)) { it.commands.pos1Set }
    }

    @Subcommand("pos2|p2")
    fun pos2(player: Player) {
        val minesPlayer = minesPlayerStorage[player]
        val newPos = player.location.toWorldPoint()
        minesPlayer.selection2 = newPos
        if (minesPlayer.selection1 != null && minesPlayer.selection1?.world != newPos.world) {
            minesPlayer.selection1 = null
            langService.send(player) { it.commands.pos1Reset }
        }
        langService.send(player, mapOf("{x}" to newPos.x, "{y}" to newPos.y, "{z}" to newPos.z)) { it.commands.pos2Set }
    }
}
