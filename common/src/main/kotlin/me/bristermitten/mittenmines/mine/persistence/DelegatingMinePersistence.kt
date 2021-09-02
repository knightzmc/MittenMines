package me.bristermitten.mittenmines.mine.persistence

import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.persistence.DelegatingPersistence
import me.bristermitten.mittenmines.persistence.PersistenceConfig
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

class DelegatingMinePersistence @Inject constructor(
    config: Provider<PersistenceConfig>,
    persistences: MinePersistences,
) : DelegatingPersistence<UUID, Mine, MinePersistence>(config, persistences), MinePersistence {}
