package me.bristermitten.mittenmines.mine.persistence

import com.google.gson.Gson
import dev.misfitlabs.kotlinguice4.typeLiteral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.bristermitten.mittenmines.mine.Mine
import org.bukkit.plugin.Plugin
import java.nio.file.Path
import java.util.*
import javax.inject.Inject
import kotlin.io.path.exists
import kotlin.io.path.reader
import kotlin.io.path.writer

class JSONMinePersistence@Inject constructor(private val gson: Gson, plugin: Plugin) : MinePersistence {
    private val file = plugin.dataFolder.toPath().resolve("mines.json")

    override suspend fun save(value: Mine) = withContext(Dispatchers.IO) {
        val all = loadAll().toMutableSet()
        all.add(value)
        saveAll(all)
    }

    override suspend fun load(id: UUID): Mine? = withContext(Dispatchers.IO) {
        loadAll().firstOrNull { it.id == id }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun loadAll(): Collection<Mine> = withContext(Dispatchers.IO) {
        if (!file.exists()) {
            return@withContext emptySet()
        }
        gson.fromJson(file.reader(), typeLiteral<Collection<Mine>>().type)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun saveAll(values: Collection<Mine>) = withContext(Dispatchers.IO) {
        gson.toJson(values, file.writer())
    }
}
