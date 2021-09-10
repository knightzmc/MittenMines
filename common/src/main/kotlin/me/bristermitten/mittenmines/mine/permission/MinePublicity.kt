package me.bristermitten.mittenmines.mine.permission

import java.util.*

sealed interface MinePublicity {
    fun canEnter(uuid: UUID): Boolean
}

object OpenPublicity : MinePublicity {
    override fun canEnter(uuid: UUID) = true
}

object ClosedPublicity : MinePublicity {
    override fun canEnter(uuid: UUID) = false
}

@JvmInline
value class WhitelistPublicity(private val allowed: Set<UUID>) : MinePublicity {
    override fun canEnter(uuid: UUID) = uuid in allowed
}

@JvmInline
value class BlacklistPublicity(private val disallowed: Set<UUID>) : MinePublicity {
    override fun canEnter(uuid: UUID) = uuid !in disallowed
}
