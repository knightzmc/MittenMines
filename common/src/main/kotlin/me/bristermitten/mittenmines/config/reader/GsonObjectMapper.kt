package me.bristermitten.mittenmines.config.reader

import com.google.gson.Gson
import me.bristermitten.mittenmines.util.CompositeType
import javax.inject.Inject

class GsonObjectMapper @Inject constructor(private val gson: Gson) : ObjectMapper {
    override fun <T> map(map: Map<Any, Any>, type: Class<T>): Result<T> = kotlin.runCatching {
        val tree = gson.toJsonTree(map)
        gson.fromJson(tree, type)
    }

    override fun <T> map(t: T): Map<Any, Any> {
        val tree = gson.toJson(t)
        return gson.fromJson(tree, CompositeType(Map::class.java, Any::class.java, Any::class.java))
    }
}
