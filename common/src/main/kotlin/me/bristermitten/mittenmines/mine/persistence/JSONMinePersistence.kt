package me.bristermitten.mittenmines.mine.persistence

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.bristermitten.mittenmines.mine.Mine
import org.bukkit.plugin.Plugin
import java.util.*
import javax.inject.Inject
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

class JSONMinePersistence @Inject constructor(private val json: Json, plugin: Plugin) : MinePersistence {
    private val file = plugin.dataFolder.toPath().resolve("mines.json")

    override suspend fun save(value: Mine) = withContext(Dispatchers.IO) {
        val all = loadAll().toMutableSet()
        all.add(value)
        saveAll(all)
    }

    override suspend fun load(id: UUID): Mine? = withContext(Dispatchers.IO) {
        loadAll().firstOrNull { it.id == id }
    }

    override suspend fun delete(value: UUID) = withContext(Dispatchers.IO) {
        val all = loadAll().toMutableList()
        all.removeIf { it.id == value }
        saveAll(all)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun loadAll(): Collection<Mine> = withContext(Dispatchers.IO) {
        if (!file.exists()) {
            return@withContext emptySet()
        }
        json.decodeFromString(file.readText())
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun saveAll(values: Collection<Mine>) = withContext(Dispatchers.IO) {
        file.writeText(json.encodeToString(values))
    }
}
