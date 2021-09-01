package me.bristermitten.mittenmines.serialization.gson

import com.google.gson.Gson
import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.module.MinesModule

object GsonModule : MinesModule, KotlinModule() {
    override val id = "json"
    override val guiceModule = this

    override fun configure() {
        bind<Gson>().toProvider<GsonProvider>()
    }
}
