package me.bristermitten.mittenmines.commands.args

import co.aikar.commands.BukkitCommandCompletionContext
import co.aikar.commands.BukkitCommandExecutionContext
import me.bristermitten.mittenmines.commands.CommandErrors
import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.mine.storage.MineStorage
import java.util.*
import javax.inject.Inject

class MineProcessor @Inject constructor(
    private val commandErrors: CommandErrors,
    private val mineStorage: MineStorage,
) :
    ArgumentProcessor<Mine> {

    override val id = "mines"
    override val type = Mine::class.java


    override fun getContext(context: BukkitCommandExecutionContext): Mine {
        val arg = context.popFirstArg()
        return if (arg.contains("-")) {
            // try interpret it as a uuid
            runCatching { UUID.fromString(arg) }
                .map(mineStorage::lookupMine)
                .map { it ?: throw Throwable() }
                .getOrElse {
                    commandErrors.commandError(context, mapOf("{value}" to arg)) { it.errors.unknownMine }
                }
        } else {
            mineStorage.getAll().firstOrNull { it.name == arg }
                ?: commandErrors.commandError(context, mapOf("{value}" to arg)) { it.errors.unknownMine }
        }

    }

    override fun getCompletions(context: BukkitCommandCompletionContext): Collection<String> {
        return mineStorage.getAll().map { it.name ?: it.id.toString() }
    }

}
