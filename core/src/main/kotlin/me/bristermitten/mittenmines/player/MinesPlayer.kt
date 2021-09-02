package me.bristermitten.mittenmines.player

import me.bristermitten.mittenmines.entity.WorldBlockPoint
import me.bristermitten.mittenmines.entity.WorldPoint
import java.util.*

data class MinesPlayer(
    val uuid: UUID,
    var selection1: WorldBlockPoint? = null,
    var selection2: WorldBlockPoint? = null,
)
