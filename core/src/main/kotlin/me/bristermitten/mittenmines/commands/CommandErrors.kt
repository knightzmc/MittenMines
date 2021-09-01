package me.bristermitten.mittenmines.commands

import co.aikar.commands.BukkitCommandExecutionContext
import co.aikar.commands.InvalidCommandArgument
import me.bristermitten.mittenmines.lang.LangConfig
import me.bristermitten.mittenmines.lang.LangElement
import me.bristermitten.mittenmines.lang.LangService
import javax.inject.Inject

class CommandErrors @Inject constructor(private val langService: LangService) {

    fun commandError(
        bukkitCommandExecutionContext: BukkitCommandExecutionContext,
        replacements: Map<String, Any> = emptyMap(),
        message: (LangConfig) -> LangElement,
    ): Nothing {
        langService.send(bukkitCommandExecutionContext.sender, replacements, message)
        throw InvalidCommandArgument(false)
    }
}
