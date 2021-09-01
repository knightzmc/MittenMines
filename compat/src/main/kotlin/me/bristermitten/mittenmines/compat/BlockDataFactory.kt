package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.block.BlockData
import org.bukkit.block.Block

interface BlockDataFactory {
    fun createBlockData(bukkitBlock: Block) : BlockData
}
