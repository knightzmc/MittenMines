package me.bristermitten.mittenmines.config.reader

import java.nio.file.Path

interface ObjectLoader {
    fun load(source: Path): Map<Any, Any>
}
