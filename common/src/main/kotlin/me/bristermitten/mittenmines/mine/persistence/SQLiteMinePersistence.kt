package me.bristermitten.mittenmines.mine.persistence

import me.bristermitten.mittenmines.mine.Mine
import java.util.*

class SQLiteMinePersistence : MinePersistence {
    override suspend fun save(value: Mine) {
        TODO("Not yet implemented")
    }

    override suspend fun load(id: UUID): Mine? {
        TODO("Not yet implemented")
    }

    override suspend fun loadAll(): Collection<Mine> {
        TODO("Not yet implemented")
    }

    override suspend fun saveAll(values: Collection<Mine>) {
        TODO("Not yet implemented")
    }

}
