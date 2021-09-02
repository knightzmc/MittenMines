package me.bristermitten.mittenmines.config

import com.google.inject.TypeLiteral
import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.config.reader.GsonObjectMapper
import me.bristermitten.mittenmines.config.reader.ObjectLoader
import me.bristermitten.mittenmines.config.reader.ObjectMapper
import me.bristermitten.mittenmines.config.reader.YamlObjectLoader
import me.bristermitten.mittenmines.module.MinesModule
import me.bristermitten.mittenmines.util.CompositeType

class ConfigModule(configurations: Set<Configuration<*>>) : MinesModule, KotlinModule() {
    private val configs =
        configurations.associate { it.type to SimpleConfigurationProvider(it) }

    override val id = "config"
    override val guiceModule = this

    override fun configure() {
        configs.forEach { (key, provider) ->
            bind<ObjectMapper>().to(GsonObjectMapper::class.java)
            bind<ObjectLoader>().to(YamlObjectLoader::class.java)

            @Suppress("UNCHECKED_CAST")
            bind(key as Class<in Any>).toProvider(provider)

            @Suppress("UNCHECKED_CAST")
            bind(TypeLiteral.get(CompositeType(ConfigurationProvider::class.java,
                key)) as TypeLiteral<ConfigurationProvider<in Any>>)
                .toInstance(provider as ConfigurationProvider<in Any>)
        }
    }
}
