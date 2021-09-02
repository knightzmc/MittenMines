package me.bristermitten.mittenmines.mine.persistence

import dev.misfitlabs.kotlinguice4.KotlinModule

object MinePersistencesModule : KotlinModule() {
    override fun configure() {
        bind<MinePersistence>().to<DelegatingMinePersistence>()
    }
}
