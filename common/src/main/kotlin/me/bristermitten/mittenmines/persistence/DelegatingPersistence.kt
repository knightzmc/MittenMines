package me.bristermitten.mittenmines.persistence

import javax.inject.Inject
import javax.inject.Provider

abstract class DelegatingPersistence<U, T, P : Persistence<U, T>> @Inject constructor(
    val config: Provider<PersistenceConfig>,
    private val persistences: Persistences<U, T, P>,
) : Persistence<U, T> {

    private fun getDelegate() =
        when (config.get().type) {
            PersistenceConfig.PersistenceType.JSON -> persistences.json
            PersistenceConfig.PersistenceType.MARIADB -> persistences.mariadb
            PersistenceConfig.PersistenceType.SQLITE -> persistences.sqlite
        }


    override suspend fun save(value: T) = getDelegate().save(value)

    override suspend fun load(id: U): T? = getDelegate().load(id)

    override suspend fun loadAll(): Collection<T> = getDelegate().loadAll()

    override suspend fun saveAll(values: Collection<T>) = getDelegate().saveAll(values)
}
