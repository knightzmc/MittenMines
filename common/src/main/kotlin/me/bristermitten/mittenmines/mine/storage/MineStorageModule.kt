package me.bristermitten.mittenmines.mine.storage

import dev.misfitlabs.kotlinguice4.KotlinModule

object MineStorageModule : KotlinModule() {
    override fun configure() {
        bind<MineStorage>().to<CachingMineStorage>()
    }
}
