package me.bristermitten.mittenmines.block

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class BlockData(val type: Material, val data: Byte? = null, val bukkitData: Any? = null) {
    fun toItemStack(): ItemStack {
        return ItemStack(type, 1, data?.toShort() ?: 0)
    }
}
