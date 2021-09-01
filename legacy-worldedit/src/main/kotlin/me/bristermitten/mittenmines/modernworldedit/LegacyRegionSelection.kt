package me.bristermitten.mittenmines.modernworldedit

import com.sk89q.worldedit.bukkit.WorldEditPlugin
import com.sk89q.worldedit.regions.CuboidRegion
import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.entity.Region
import org.bukkit.entity.Player

object LegacyRegionSelection : RegionSelection {
    override fun getSelection(player: Player): Region? {
        val selection = WorldEditPlugin.getPlugin(WorldEditPlugin::class.java)
            .getSelection(player)
        val selector = selection.regionSelector
        if (selector.isDefined.not()) {
            return null
        }
        val weRegion = CuboidRegion.makeCuboid(selector.region)
        return Region.of(
            weRegion.world?.name ?: player.world.name,
            weRegion.minimumPoint.toMinesBlockPoint(),
            weRegion.maximumPoint.toMinesBlockPoint(),
        )
    }
}
