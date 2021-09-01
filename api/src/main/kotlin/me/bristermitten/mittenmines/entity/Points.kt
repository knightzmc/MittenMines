package me.bristermitten.mittenmines.entity

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

data class BlockPoint(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    fun toLocation(world: World) = Location(world, x.toDouble(), y.toDouble(), z.toDouble())

    fun withWorld(world: String) = WorldPoint(world, x.toDouble(), y.toDouble(), z.toDouble())
}

data class Point(
    val x: Double,
    val y: Double,
    val z: Double,
) {
    fun toLocation(world: World) = Location(world, x, y, z)
}

data class WorldPoint(
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double,
) {
    fun toLocation() = Bukkit.getWorld(world)?.let { world -> Location(world, x, y, z) }
}

fun Location.toWorldPoint() = WorldPoint(world.name, x, y, z)
