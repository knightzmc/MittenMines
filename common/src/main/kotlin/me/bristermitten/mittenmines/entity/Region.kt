package me.bristermitten.mittenmines.entity

import me.bristermitten.mittenmines.util.minMax

data class Region private constructor(
    val world: String,
    val min: BlockPoint,
    val max: BlockPoint,
) {
    fun realised(): Region {
        val (minX, maxX) = minMax(min.x, max.x)
        val (minY, maxY) = minMax(min.y, max.y)
        val (minZ, maxZ) = minMax(min.z, max.z)

        return Region(
            world,
            BlockPoint(minX, minY, minZ),
            BlockPoint(maxX, maxY, maxZ),
        )
    }

    val points: Sequence<BlockPoint>
        get() = sequence {
            for (x in min.x..max.x) {
                for (y in min.y..max.y) {
                    for (z in min.z..max.z) {
                        yield(BlockPoint(x, y, z))
                    }
                }
            }
        }

    companion object {
        fun of(world: String, min: BlockPoint, max: BlockPoint): Region {
            return Region(world, min, max).realised()
        }
    }
}
