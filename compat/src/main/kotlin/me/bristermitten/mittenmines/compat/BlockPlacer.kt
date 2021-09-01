package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.entity.Region
import me.bristermitten.mittenmines.entity.WorldPoint

interface BlockPlacer {
    fun set(blockPattern: BlockPattern, point: WorldPoint)

    fun setMultiple(blockPattern: BlockPattern, points: Collection<WorldPoint>) =
        points.forEach { this.set(blockPattern, it) }

    fun setRegion(blockPattern: BlockPattern, region: Region) = region.points
        .map { point -> point.withWorld(region.world) }
        .forEach { this.set(blockPattern, it) }
}
