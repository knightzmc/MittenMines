package me.bristermitten.mittenmines.mine.persistence

import com.google.gson.Gson
import me.bristermitten.mittenmines.mine.Mine
import java.nio.file.Path
import java.util.*

class MariaMinePersistence() : MinePersistence {
    override suspend fun save(value: Mine) {
        TODO("Not yet implemented")
    }

    override suspend fun load(id: UUID): Mine? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: UUID) {
        TODO("Not yet implemented")
    }

    override suspend fun loadAll(): Collection<Mine> {
        TODO("Not yet implemented")
    }

    override suspend fun saveAll(values: Collection<Mine>) {
        TODO("Not yet implemented")
    }
}
