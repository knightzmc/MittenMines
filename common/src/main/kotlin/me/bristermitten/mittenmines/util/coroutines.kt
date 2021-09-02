package me.bristermitten.mittenmines.util

import kotlinx.coroutines.*
import me.bristermitten.mittenmines.trait.HasPlugin
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext

val HasPlugin.syncDispatcher get() = plugin.dispatcher(false)
val HasPlugin.asyncDispatcher get() = plugin.dispatcher(true)

/*
Credit: https://github.com/okkero/Skedule/blob/master/src/main/kotlin/com/okkero/skedule/BukkitDispatcher.kt
 */
private val bukkitScheduler: BukkitScheduler
    get() = Bukkit.getScheduler()


@OptIn(InternalCoroutinesApi::class)
class BukkitDispatcher(private val plugin: Plugin, private val async: Boolean = false) : CoroutineDispatcher(), Delay {

    private val runTaskLater: (Plugin, Runnable, Long) -> BukkitTask =
        if (async)
            bukkitScheduler::runTaskLaterAsynchronously
        else
            bukkitScheduler::runTaskLater
    private val runTask: (Plugin, Runnable) -> BukkitTask =
        if (async)
            bukkitScheduler::runTaskAsynchronously
        else
            bukkitScheduler::runTask


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        val task = runTaskLater(
            plugin,
            Runnable {
                continuation.apply { resumeUndispatched(Unit) }
            },
            timeMillis / 50)
        continuation.invokeOnCancellation { task.cancel() }
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if (!context.isActive) {
            return
        }

        if (!async && Bukkit.isPrimaryThread()) {
            block.run()
        } else {
            runTask(plugin, block)
        }
    }

}

fun JavaPlugin.dispatcher(async: Boolean = false) = BukkitDispatcher(this, async)
