package me.bristermitten.mittenmines.item

import kotlinx.serialization.Serializable
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Serializable
data class ItemData(val type: Material, val durability: Short = 0) {
    fun toItemStack() = ItemStack(type, 1, durability)
}
