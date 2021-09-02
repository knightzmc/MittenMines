package me.bristermitten.mittenmines.modernworldedit

import me.bristermitten.mittenmines.block.BlockData
import me.bristermitten.mittenmines.compat.BlockDataFactory
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

object ModernBlockDataFactory : BlockDataFactory {
    override fun createBlockData(bukkitBlock: Block): BlockData {
        return BlockData(bukkitBlock.type, null, bukkitBlock.blockData)
    }

    override fun createBlockData(item: ItemStack): BlockData {
        return BlockData(item.type, null, item.type.createBlockData())
    }

    override fun createBlockData(type: Material, data: Byte?): BlockData {
        return BlockData(type, null, type.createBlockData())
    }
}
