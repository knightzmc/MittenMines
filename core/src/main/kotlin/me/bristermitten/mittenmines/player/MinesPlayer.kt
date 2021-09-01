package me.bristermitten.mittenmines.player

import me.bristermitten.mittenmines.entity.WorldPoint
import java.util.*

data class MinesPlayer(
    val uuid: UUID,
    var selection1: WorldPoint? = null,
    var selection2: WorldPoint? = null,
)
