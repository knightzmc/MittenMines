package me.bristermitten.mittenmines.mine

import kotlinx.serialization.Serializable
import me.bristermitten.mittenmines.item.ItemData
import me.bristermitten.mittenmines.mine.permission.MinePublicity
import me.bristermitten.mittenmines.mine.permission.OpenPublicity
import org.bukkit.Material

@Serializable
data class MineOptions(
    var allowFlight: Boolean = true,
    var displayName: String? = null,
    var description: String? = null,
    var icon: ItemData = ItemData(Material.IRON_PICKAXE),
    var tax: Float? = null,
    var publicity: MinePublicity = OpenPublicity
) {
}
