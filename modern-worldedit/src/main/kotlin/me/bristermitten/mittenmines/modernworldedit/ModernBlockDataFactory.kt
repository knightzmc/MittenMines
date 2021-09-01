package me.bristermitten.mittenmines.modernworldedit

import me.bristermitten.mittenmines.block.BlockData
import me.bristermitten.mittenmines.compat.BlockDataFactory
import org.bukkit.Material
import org.bukkit.block.Block

object ModernBlockDataFactory : BlockDataFactory {
    override fun createBlockData(bukkitBlock: Block): BlockData {
        return BlockData(bukkitBlock.type, null, bukkitBlock.blockData)
    }

    override fun createBlockData(type: Material, data: Byte?): BlockData {
        return BlockData(type, null, type.createBlockData())
    }
}
