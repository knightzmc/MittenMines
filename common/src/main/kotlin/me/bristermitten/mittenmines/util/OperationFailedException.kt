package me.bristermitten.mittenmines.util

import me.bristermitten.mittenmines.lang.LangConfig
import me.bristermitten.mittenmines.lang.LangElement
import me.bristermitten.mittenmines.lang.LangService
import org.bukkit.command.CommandSender

class OperationFailedException(
    private val placeholders: Map<String, Any>,
    private val lang: (LangConfig) -> LangElement,
) :
    Exception() {
    fun report(langService: LangService, sender: CommandSender) = langService.send(sender, placeholders, lang)
}
