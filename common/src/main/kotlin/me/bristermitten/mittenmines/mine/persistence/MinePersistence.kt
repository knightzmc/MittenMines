package me.bristermitten.mittenmines.mine.persistence

import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.persistence.Persistence
import java.util.*

interface MinePersistence : Persistence<UUID, Mine> {

}
