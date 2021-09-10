package me.bristermitten.mittenmines.serialization.json

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import javax.inject.Inject
import javax.inject.Provider

class JSONProvider @Inject constructor(private val modules: Set<SerializersModule>) : Provider<Json> {

    override fun get(): Json {
        return Json {
            prettyPrint = true
            encodeDefaults = true

            serializersModule = SerializersModule {
                modules.forEach(this::include)
            }
        }
    }
}
