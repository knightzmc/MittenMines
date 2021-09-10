package me.bristermitten.mittenmines.mine

import kotlinx.serialization.Serializable
import me.bristermitten.mittenmines.serialization.kotlinx.UUIDSerializer
import java.util.*

@Serializable
sealed class MineOwner

@Serializable
data class PlayerOwner(@Serializable(with = UUIDSerializer::class) val uuid: UUID) : MineOwner()

@Serializable
object ServerOwner : MineOwner()
