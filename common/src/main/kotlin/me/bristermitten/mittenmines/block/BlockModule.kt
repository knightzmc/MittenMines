package me.bristermitten.mittenmines.block

import com.google.gson.TypeAdapterFactory
import com.google.inject.multibindings.Multibinder
import dev.misfitlabs.kotlinguice4.KotlinModule
import kotlinx.serialization.modules.SerializersModule
import me.bristermitten.mittenmines.module.MinesModule

object BlockModule : MinesModule, KotlinModule() {
    override val id = "block"
    override val guiceModule = this

    override fun configure() {
        Multibinder.newSetBinder(binder(), SerializersModule::class.java)
            .addBinding().toProvider(BlockPatternSerializers::class.java)
    }
}
