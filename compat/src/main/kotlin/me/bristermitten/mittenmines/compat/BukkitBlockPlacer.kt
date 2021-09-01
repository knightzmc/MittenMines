package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.entity.WorldPoint

object BukkitBlockPlacer : BlockPlacer {
    override fun set(blockPattern: BlockPattern, point: WorldPoint) {
        val location = point.toLocation() ?: throw IllegalArgumentException("World not found for Point")
        val data = blockPattern.createData()
        val block = location.block
        block.setType(data.type, false)
        @Suppress("DEPRECATION")
        block.setData(data.data ?: 0, false)
    }
}
