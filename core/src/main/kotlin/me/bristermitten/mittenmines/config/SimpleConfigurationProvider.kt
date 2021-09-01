package me.bristermitten.mittenmines.config

import com.google.inject.Inject
import me.bristermitten.mittenmines.config.reader.ConfigurationReader
import me.bristermitten.mittenmines.util.Cached
import me.bristermitten.mittenmines.util.toPath
import org.bukkit.plugin.Plugin
import java.nio.file.Files
import java.util.concurrent.ConcurrentHashMap

class SimpleConfigurationProvider<T>(private val source: Configuration<T>) : ConfigurationProvider<T> {
    private val invalidationHooks = ConcurrentHashMap.newKeySet<(T) -> Unit>()
    private lateinit var cached: Cached<T>
    override fun addInvalidationHook(onInvalidation: (T) -> Unit) {
        invalidationHooks += onInvalidation
    }

    override fun get(): T {
        return cached.get()
    }

    @Inject
    fun init(plugin: Plugin, reader: ConfigurationReader, fileWatcherService: FileWatcherService) {
        if (this::cached.isInitialized) {
            throw IllegalStateException("Already initialised")
        }
        val realisedPath = plugin.dataFolder.toPath().resolve(source.fileName)
        fileWatcherService.add(FileWatcher(
            realisedPath
        ) {
            invalidationHooks.forEach { it(get()) }
            cached.invalidate()
        })

        this.cached = Cached {
            val resource = plugin.javaClass.classLoader.getResource(source.fileName)
                ?: throw IllegalArgumentException("Could not find ${source.fileName} in jar")
            val pathInJar = resource.toPath()

            if (!Files.exists(realisedPath)) {
                Files.createDirectories(realisedPath.parent)
                Files.copy(pathInJar, realisedPath)
            }
            reader.load(source.type, realisedPath)
                .getOrThrow()
        }
    }
}
