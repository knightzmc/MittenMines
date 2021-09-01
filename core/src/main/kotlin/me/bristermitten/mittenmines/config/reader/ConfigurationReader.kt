package me.bristermitten.mittenmines.config.reader

import com.google.inject.Inject
import java.nio.file.Path

class ConfigurationReader @Inject constructor(
    private val loader: ObjectLoader,
    private val mapper: ObjectMapper,
) {

    fun <T> load(type: Class<T>, source: Path) =
        mapper.map(loader.load(source), type)
}
