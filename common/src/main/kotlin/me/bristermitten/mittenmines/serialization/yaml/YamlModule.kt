package me.bristermitten.mittenmines.serialization.yaml

import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.module.MinesModule
import org.yaml.snakeyaml.Yaml

object YamlModule : MinesModule, KotlinModule() {
    override val id = "yaml"
    override val guiceModule = this

    override fun configure() {
        bind<Yaml>().toProvider<SnakeYamlProvider>()
    }
}
