package me.bristermitten.mittenmines.player

import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.module.MinesModule

object PlayerModule : MinesModule, KotlinModule() {
    override val id = "players"
    override val guiceModule = this

    override fun configure() {
        bind<MinesPlayerStorage>()
    }
}
