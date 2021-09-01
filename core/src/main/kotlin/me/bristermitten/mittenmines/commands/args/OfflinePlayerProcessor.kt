package me.bristermitten.mittenmines.commands.args

import co.aikar.commands.BukkitCommandCompletionContext
import co.aikar.commands.BukkitCommandExecutionContext
import me.bristermitten.mittenmines.commands.CommandErrors
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.*
import javax.inject.Inject

class OfflinePlayerProcessor @Inject constructor(private val commandErrors: CommandErrors) :
    ArgumentProcessor<OfflinePlayer> {

    override val id = "offlinePlayers"
    override val type = OfflinePlayer::class.java


    override fun getContext(context: BukkitCommandExecutionContext): OfflinePlayer {
        val arg: String = context.popFirstArg()
        @Suppress("DEPRECATION")
        return if (arg.contains("-")) {
            // try interpret it as a uuid
            runCatching { UUID.fromString(arg) }
                .map(Bukkit::getOfflinePlayer)
                .getOrElse {
                    commandErrors.commandError(context, mapOf("{value}" to arg)) { it.errors.unknownPlayer }
                }
        } else {
            Bukkit.getOfflinePlayer(arg)
                ?: commandErrors.commandError(context, mapOf("{value}" to arg)) { it.errors.unknownPlayer }
        }

    }

    override fun getCompletions(context: BukkitCommandCompletionContext): Collection<String> {
        return Bukkit.getOfflinePlayers().map { it.name }
    }

}
