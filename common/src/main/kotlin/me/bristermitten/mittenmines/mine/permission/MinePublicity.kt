package me.bristermitten.mittenmines.mine.permission

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
sealed class MinePublicity {
    abstract fun canEnter(uuid: UUID): Boolean
}

object OpenPublicity : MinePublicity() {
    override fun canEnter(uuid: UUID) = true
}

object ClosedPublicity : MinePublicity() {
    override fun canEnter(uuid: UUID) = false
}

data class WhitelistPublicity(val allowed: Set<UUID>) : MinePublicity() {
    override fun canEnter(uuid: UUID) = uuid in allowed
}

data class BlacklistPublicity(val disallowed: Set<UUID>) : MinePublicity() {
    override fun canEnter(uuid: UUID) = uuid !in disallowed
}
