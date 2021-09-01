package me.bristermitten.mittenmines.config

import java.nio.file.Path
import java.nio.file.WatchEvent

data class FileWatcher(val watching: Path, val onModify: (WatchEvent<*>) -> Unit)
