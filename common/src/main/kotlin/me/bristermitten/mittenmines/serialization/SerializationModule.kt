package me.bristermitten.mittenmines.serialization

import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.module.MinesModule
import me.bristermitten.mittenmines.serialization.gson.GsonModule
import me.bristermitten.mittenmines.serialization.json.JSONModule
import me.bristermitten.mittenmines.serialization.yaml.YamlModule

object SerializationModule : MinesModule, KotlinModule() {
    override val id = "serialization"
    override val guiceModule = this
    override val children = setOf<MinesModule>(
        JSONModule,
        GsonModule,
        YamlModule
    )
}
