package me.bristermitten.mittenmines.serialization.json

import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Provider

class JSONProvider @Inject constructor() : Provider<Json> {

    override fun get(): Json {
        return Json {
            prettyPrint = true
            encodeDefaults = true
        }
    }
}
