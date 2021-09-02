package me.bristermitten.mittenmines.persistence

import me.bristermitten.mittenmines.config.config

data class PersistenceConfig(
    val type: PersistenceType,
    val database: DatabaseConfig,
) {
    companion object {
        val CONFIG = config<PersistenceConfig>("storage.yml")
    }

    data class DatabaseConfig(
        val host: String,
        val database: String,
        val username: String,
        val password: String,
        val port: Int,
    )

    enum class PersistenceType {
        JSON,
        SQLITE,
        MARIADB
    }
}
