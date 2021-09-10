package me.bristermitten.mittenmines.serialization.gson

import com.google.gson.Gson
import com.google.gson.TypeAdapterFactory
import com.google.inject.multibindings.Multibinder
import dev.misfitlabs.kotlinguice4.KotlinModule
import kotlinx.serialization.json.Json
import me.bristermitten.mittenmines.module.MinesModule

object GsonModule : MinesModule, KotlinModule() {
    override val id = "gson"
    override val guiceModule = this

    override fun configure() {
        bind<Gson>().toProvider<GsonProvider>()

        Multibinder.newSetBinder(binder(), TypeAdapterFactory::class.java)
            .addBinding()
    }
}
