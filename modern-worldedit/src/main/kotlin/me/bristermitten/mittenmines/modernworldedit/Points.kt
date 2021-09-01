package me.bristermitten.mittenmines.modernworldedit


import com.sk89q.worldedit.math.BlockVector3
import me.bristermitten.mittenmines.entity.BlockPoint
import me.bristermitten.mittenmines.entity.WorldPoint

fun WorldPoint.toVector(): BlockVector3 = BlockVector3.at(this.x, this.y, this.z)
fun BlockPoint.toVector(): BlockVector3 = BlockVector3.at(this.x, this.y, this.z)

fun BlockVector3.toMinesBlockPoint() = BlockPoint(this.blockX, this.blockY, this.blockZ)
