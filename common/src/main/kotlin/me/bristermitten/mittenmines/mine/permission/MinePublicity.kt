package me.bristermitten.mittenmines.mine.permission

import kotlinx.serialization.Serializable
import me.bristermitten.mittenmines.serialization.kotlinx.UUIDSerializer
import java.util.*

@Serializable
sealed class MinePublicity {
    abstract fun canEnter(uuid: UUID): Boolean
}

@Serializable
object OpenPublicity : MinePublicity() {
    override fun canEnter(uuid: UUID) = true
}

@Serializable
object ClosedPublicity : MinePublicity() {
    override fun canEnter(uuid: UUID) = false
}

@Serializable
data class WhitelistPublicity(val allowed: Set<@Serializable(with = UUIDSerializer::class) UUID>) : MinePublicity() {
    override fun canEnter(uuid: UUID) = uuid in allowed
}

@Serializable
data class BlacklistPublicity(val disallowed: Set<@Serializable(with = UUIDSerializer::class) UUID>) : MinePublicity() {
    override fun canEnter(uuid: UUID) = uuid !in disallowed
}
