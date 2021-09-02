package me.bristermitten.mittenmines.mine

import me.bristermitten.mittenmines.entity.AngledWorldPoint
import me.bristermitten.mittenmines.entity.Region
import java.util.*

data class Mine(
    val id: UUID,
    val owner: MineOwner,
    var name: String?,
    var region: Region,
    var miningRegion: Region,
    var spawnLocation: AngledWorldPoint,
)
