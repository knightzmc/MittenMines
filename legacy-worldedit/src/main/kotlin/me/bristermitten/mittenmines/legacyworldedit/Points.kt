package me.bristermitten.mittenmines.legacyworldedit

import com.sk89q.worldedit.Vector
import me.bristermitten.mittenmines.entity.BlockPoint
import me.bristermitten.mittenmines.entity.WorldPoint

fun WorldPoint.toVector() = Vector(this.x, this.y, this.z)
fun BlockPoint.toVector() = Vector(this.x, this.y, this.z)
