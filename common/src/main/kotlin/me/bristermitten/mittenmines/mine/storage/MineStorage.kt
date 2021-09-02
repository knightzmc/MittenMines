package me.bristermitten.mittenmines.mine.storage

import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.mine.MineOwner
import java.util.*

interface MineStorage {
    suspend fun getMine(uuid: UUID): Mine?
    fun lookupMine(uuid: UUID) : Mine?

    suspend fun save(mine: Mine)

    suspend fun fetchAll(): Collection<Mine>

    fun getAll(): Collection<Mine>

    suspend fun getMinesByOwner(owner: MineOwner): Collection<Mine>

    suspend fun saveAll()
}
