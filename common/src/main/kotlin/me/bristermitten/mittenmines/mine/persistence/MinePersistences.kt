package me.bristermitten.mittenmines.mine.persistence

import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.persistence.Persistences
import java.util.*
import javax.inject.Inject

class MinePersistences @Inject constructor(
    override val json: JSONMinePersistence,
    override val sqlite: SQLiteMinePersistence,
    override val mariadb:MariaMinePersistence
) : Persistences<UUID, Mine, MinePersistence> {
}
