package me.bristermitten.mittenmines.entity

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

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
