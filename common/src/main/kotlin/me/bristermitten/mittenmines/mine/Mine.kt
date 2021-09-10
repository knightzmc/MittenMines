package me.bristermitten.mittenmines.mine

import kotlinx.serialization.Serializable
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.entity.AngledWorldPoint
import me.bristermitten.mittenmines.entity.Region
import me.bristermitten.mittenmines.serialization.kotlinx.UUIDSerializer
import java.util.*

@Serializable
data class Mine(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val owner: MineOwner,
    /**
     * Internal name of the mine, can be used as an alias to the id
     */
    var name: String?,
    var region: Region,
    var miningRegion: Region,
    var spawnLocation: AngledWorldPoint,
    var pattern: BlockPattern,
    val options: MineOptions = MineOptions(),
)
