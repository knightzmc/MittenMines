package me.bristermitten.mittenmines.entity

import kotlinx.serialization.Serializable
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

@Serializable
data class BlockPoint(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    fun toLocation(world: World) = Location(world, x.toDouble(), y.toDouble(), z.toDouble())

    fun withWorld(world: String) = WorldPoint(world, x.toDouble(), y.toDouble(), z.toDouble())
}
@Serializable
data class Point(
    val x: Double,
    val y: Double,
    val z: Double,
) {
    fun toLocation(world: World) = Location(world, x, y, z)
}
@Serializable
data class WorldPoint(
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double,
) {
    fun toLocation() = Bukkit.getWorld(world)?.let { world -> Location(world, x, y, z) }
    fun toBlockPoint() = BlockPoint(x.toInt(), y.toInt(), z.toInt())
}
@Serializable
data class WorldBlockPoint(
    val world: String,
    val x: Int,
    val y: Int,
    val z: Int,
) {
    fun toLocation() = Bukkit.getWorld(world)?.let { world -> Location(world, x.toDouble(), y.toDouble(), z.toDouble()) }
    fun toBlockPoint() = BlockPoint(x, y, z)
}
@Serializable
data class AngledWorldPoint(
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
) {
    fun toLocation() = Bukkit.getWorld(world)?.let { world -> Location(world, x, y, z, yaw, pitch) }
}

fun Location.toWorldPoint() = WorldPoint(world.name, x, y, z)
fun Location.toWorldBlockPoint() = WorldBlockPoint(world.name, blockX, blockY, blockZ)
fun Location.toAngledWorldPoint() = AngledWorldPoint(world.name, x, y, z, yaw, pitch)
