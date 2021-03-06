package me.bristermitten.mittenmines.legacyworldedit

import me.bristermitten.mittenmines.block.BlockData
import me.bristermitten.mittenmines.compat.BlockDataFactory
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

object LegacyBlockDataFactory : BlockDataFactory {
    override fun createBlockData(bukkitBlock: Block): BlockData {
        @Suppress("DEPRECATION")
        return BlockData(bukkitBlock.type, bukkitBlock.data)
    }

    override fun createBlockData(item: ItemStack): BlockData {
        return BlockData(item.type, item.durability.toByte())
    }

    override fun createBlockData(type: Material, data: Byte?): BlockData {
        return BlockData(type, data)
    }
}
