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
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject

@CommandAlias(COMMAND_NAMES)
class MinesCommand @Inject constructor(
    private val regionSelection: RegionSelection,
    private val blockPlacer: BlockPlacer,
    private val blockDataFactory: BlockDataFactory,
) : BaseCommand() {

    @Subcommand("create")
    fun createMine(player: Player) {
        val selection = regionSelection.getSelection(player) ?: run {
            player.sendMessage("You need to select a region to be the mine")
            return
        }

        val mine = Mine(UUID.randomUUID(), ServerOwner, null, selection, player.location.toAngledWorldPoint())
        blockPlacer.setRegion(ConstantBlockPattern(blockDataFactory.createBlockData(Material.COAL_ORE)), mine.region)
        player.teleport(mine.spawnLocation.toLocation())
    }
}
