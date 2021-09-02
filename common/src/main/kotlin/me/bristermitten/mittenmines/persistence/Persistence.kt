package me.bristermitten.mittenmines.persistence

interface Persistence<U, T> {
    suspend fun save(value: T)
    suspend fun load(id: U): T?

    suspend fun loadAll(): Collection<T>
    suspend fun saveAll(values: Collection<T>)
}
