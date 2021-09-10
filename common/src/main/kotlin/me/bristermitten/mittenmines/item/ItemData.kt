package me.bristermitten.mittenmines.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class ItemData(val type: Material, val durability: Short = 0) {
    fun toItemStack() = ItemStack(type, 1, durability)
}
