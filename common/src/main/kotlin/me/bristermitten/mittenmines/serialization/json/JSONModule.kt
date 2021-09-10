package me.bristermitten.mittenmines.serialization.json

import dev.misfitlabs.kotlinguice4.KotlinModule
import kotlinx.serialization.json.Json
import me.bristermitten.mittenmines.module.MinesModule

object JSONModule : MinesModule, KotlinModule() {
    override val id = "json"
    override val guiceModule = this

    override fun configure() {
        bind<Json>().toProvider<JSONProvider>()
    }
}
