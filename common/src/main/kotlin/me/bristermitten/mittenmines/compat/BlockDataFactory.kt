package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.block.BlockData
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

interface BlockDataFactory {
    fun createBlockData(bukkitBlock: Block): BlockData
    fun createBlockData(item: ItemStack): BlockData
    fun createBlockData(type: Material, data: Byte? = null): BlockData
}
