package me.bristermitten.mittenmines.mine

import java.util.*

interface MineStorage {
    suspend fun getMine(uuid: UUID) : Mine?

    suspend fun getMinesByOwner(owner: MineOwner) : Collection<Mine>
}
