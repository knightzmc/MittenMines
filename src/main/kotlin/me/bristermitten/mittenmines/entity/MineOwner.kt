package me.bristermitten.mittenmines.entity

import java.util.*

sealed class MineOwner
data class PlayerOwner(val uuid: UUID) : MineOwner()
object ServerOwner : MineOwner()
