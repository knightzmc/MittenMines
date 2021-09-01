package me.bristermitten.mittenmines.entity

import java.util.*

data class Mine(
    val id: UUID,
    val owner: MineOwner,
    var name: String?,
    var region: Region,
    var spawnLocation: AngledWorldPoint,
)
