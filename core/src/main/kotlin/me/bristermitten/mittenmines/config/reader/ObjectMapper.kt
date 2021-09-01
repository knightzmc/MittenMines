package me.bristermitten.mittenmines.config.reader

interface ObjectMapper {
    fun <T> map(map: Map<Any, Any>, type: Class<T>) : Result<T>

    fun <T> map(t: T) : Map<Any, Any>
}
