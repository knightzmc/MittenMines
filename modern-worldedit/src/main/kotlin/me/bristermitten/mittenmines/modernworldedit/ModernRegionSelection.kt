package me.bristermitten.mittenmines.modernworldedit

import com.sk89q.worldedit.IncompleteRegionException
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.bukkit.WorldEditPlugin
import com.sk89q.worldedit.regions.CuboidRegion
import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.entity.Region
import org.bukkit.entity.Player

object ModernRegionSelection : RegionSelection {
    override fun getSelection(player: Player): Region? {
        return try {
            val selection = WorldEditPlugin.getPlugin(WorldEditPlugin::class.java)
                .getSession(player)
                .getSelection(BukkitWorld(player.world))

            val weRegion = CuboidRegion.makeCuboid(selection)
            Region.of(
                weRegion.world?.name ?: player.world.name,
                weRegion.minimumPoint.toMinesBlockPoint(),
                weRegion.maximumPoint.toMinesBlockPoint(),
            )
        } catch (e: IncompleteRegionException) {
            null
        }


    }
}
