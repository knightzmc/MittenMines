package me.bristermitten.mittenmines.modernworldedit

import com.sk89q.worldedit.WorldEdit
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.function.pattern.Patterns
import com.sk89q.worldedit.regions.CuboidRegion
import com.sk89q.worldedit.world.World
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.compat.BlockPlacer
import me.bristermitten.mittenmines.entity.Region
import me.bristermitten.mittenmines.entity.WorldPoint
import org.bukkit.Bukkit

object LegacyBlockPlacer : BlockPlacer {
    override fun set(blockPattern: BlockPattern, point: WorldPoint) {
        val world = Bukkit.getWorld(point.world)?.let(::BukkitWorld)
            ?: throw IllegalArgumentException("Cannot find world for point $point")

        WorldEdit.getInstance().editSessionFactory.getEditSession(world as World, -1)
            .setBlock(
                point.toVector(),
                Patterns.wrap(blockPattern.toWEPattern())
            )
    }

    override fun setRegion(blockPattern: BlockPattern, region: Region) {
        val world = Bukkit.getWorld(region.world)?.let(::BukkitWorld)
            ?: throw IllegalArgumentException("Cannot find world for region $region")
        val weRegion = CuboidRegion(region.min.toVector(), region.max.toVector())

        WorldEdit.getInstance().editSessionFactory.getEditSession(world as World, -1)
            .setBlocks(
                weRegion,
                Patterns.wrap(blockPattern.toWEPattern())
            )
    }
}
