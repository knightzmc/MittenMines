package me.bristermitten.mittenmines.config.reader

import org.yaml.snakeyaml.Yaml
import java.nio.file.Path
import javax.inject.Inject
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.readText

class YamlObjectLoader @Inject constructor(private val yaml: Yaml) : ObjectLoader {
    @OptIn(ExperimentalPathApi::class)
    @Suppress("UNCHECKED_CAST")
    override fun load(source: Path): Map<Any, Any> {
        return yaml.load(source.readText()) as Map<Any, Any>
    }
}
