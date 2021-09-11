package me.bristermitten.mittenmines.config

import java.io.IOException
import java.nio.file.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executors
import javax.inject.Singleton

@Singleton
class FileWatcherService : Runnable {
    private val watchers: ConcurrentMap<Path, MutableSet<FileWatcher>> = ConcurrentHashMap()
    private val thread = Executors.newSingleThreadExecutor { Thread(it, "MittenMines File Watcher") }

    fun watch() {
        thread.submit(this)
    }

    fun add(watcher: FileWatcher) {
        println("add called")
        val mutableList = watchers.getOrPut(watcher.watching) { mutableSetOf() }
        mutableList.add(watcher)
        watchers[watcher.watching] = mutableList
    }

    override fun run() {
        try {
            FileSystems.getDefault().newWatchService().use { watchService ->
                for (watcherList in watchers.values) {
                    for (watcher in watcherList) {
                        var toWatch = watcher.watching
                        if (!Files.isDirectory(toWatch)) {
                            toWatch = toWatch.parent
                        }
                        toWatch.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY)
                    }
                    var poll = true
                    while (poll) {
                        poll = pollEvents(watchService)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        } catch (e: InterruptedException) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }
    }


    private fun pollEvents(watchService: WatchService): Boolean {
        val key = watchService.take()
        val at = key.watchable() as Path
        for (pollEvent in key.pollEvents()) {
            if (pollEvent.kind() != StandardWatchEventKinds.ENTRY_MODIFY && pollEvent.kind() != StandardWatchEventKinds.ENTRY_DELETE) {
                continue
            }
            val fileWatchers = watchers[at.resolve(pollEvent.context() as Path)]
                ?: return key.reset() //not relevant
            for (watcher in fileWatchers) {
                watcher.onModify(pollEvent)
            }
        }
        return key.reset()
    }
}
