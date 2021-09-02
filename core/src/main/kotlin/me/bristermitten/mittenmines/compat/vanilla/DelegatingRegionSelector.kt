package me.bristermitten.mittenmines.compat.vanilla

import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.entity.Region
import org.bukkit.entity.Player

class DelegatingRegionSelector(private val vanilla: VanillaRegionSelection, private val other: RegionSelection) :
    RegionSelection {
    override fun getSelection(player: Player): Region? {
        return vanilla.getSelection(player) ?: other.getSelection(player)
    }
}
