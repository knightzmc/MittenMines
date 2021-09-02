package me.bristermitten.mittenmines.mine

import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.mine.persistence.MinePersistencesModule
import me.bristermitten.mittenmines.mine.storage.MineStorageModule
import me.bristermitten.mittenmines.module.MinesModule

object MineModule : MinesModule, KotlinModule() {
    override val id = "mines"
    override val guiceModule = this

    override fun configure() {
        install(MinePersistencesModule)
        install(MineStorageModule)
    }
}
