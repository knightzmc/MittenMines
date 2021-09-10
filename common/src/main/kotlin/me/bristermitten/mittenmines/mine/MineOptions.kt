package me.bristermitten.mittenmines.mine

import me.bristermitten.mittenmines.item.ItemData
import org.bukkit.Material

data class MineOptions(
    var allowFlight: Boolean = true,
    var displayName: String? = null,
    var description: String? = null,
    var icon: ItemData = ItemData(Material.IRON_PICKAXE),
    var tax: Float? = null,
) {
}
