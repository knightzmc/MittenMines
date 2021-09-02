package me.bristermitten.mittenmines

import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.module.MinesModule
import org.bukkit.plugin.Plugin

class CoreModule(private val mittenMines: MittenMines) : MinesModule, KotlinModule() {
    override val id: String = "core"
    override val guiceModule = this

    override fun configure() {
        bind<Plugin>().to<MittenMines>()
        bind<MittenMines>().toInstance(mittenMines)

    }

}
