package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.block.BlockData
import org.bukkit.Material
import org.bukkit.block.Block

interface BlockDataFactory {
    fun createBlockData(bukkitBlock: Block): BlockData
    fun createBlockData(type: Material, data: Byte? = null): BlockData
}
