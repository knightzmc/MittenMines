package me.bristermitten.mittenmines.mine.storage

import com.google.common.cache.CacheBuilder
import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.mine.MineOwner
import me.bristermitten.mittenmines.mine.persistence.MinePersistence
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachingMineStorage @Inject constructor(private val minePersistence: MinePersistence) : MineStorage {
    private val cache = CacheBuilder.newBuilder().build<UUID, Mine>()

    override suspend fun getMine(uuid: UUID): Mine? {
        val inCache = cache.getIfPresent(uuid)
        if (inCache != null) {
            return inCache
        }
        val loaded = minePersistence.load(uuid) ?: return null
        cache.put(uuid, loaded)
        return loaded
    }

    override fun lookupMine(uuid: UUID): Mine? {
        return cache.getIfPresent(uuid)
    }

    override suspend fun save(mine: Mine) {
        cache.put(mine.id, mine)
        minePersistence.save(mine)
    }

    override suspend fun fetchAll(): Collection<Mine> {
        return minePersistence.loadAll().onEach {
            cache.put(it.id, it)
        }
    }

    override fun getAll(): Collection<Mine> {
        return cache.asMap().values
    }

    override suspend fun saveAll() {
        minePersistence.saveAll(cache.asMap().values.toList())
    }

    override suspend fun delete(mine: UUID) {
        cache.invalidate(mine)
        minePersistence.delete(mine)
    }

    override suspend fun getMinesByOwner(owner: MineOwner): Collection<Mine> {
        return fetchAll().filter { it.owner == owner }
    }

}
