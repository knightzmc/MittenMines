package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.entity.Region
import org.bukkit.entity.Player

interface RegionSelection {
    fun getSelection(player: Player): Region?
}
