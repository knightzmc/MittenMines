package me.bristermitten.mittenmines.commands.args

import co.aikar.commands.CommandCompletions.AsyncCommandCompletionHandler
import co.aikar.commands.BukkitCommandCompletionContext

interface TabCompleter : AsyncCommandCompletionHandler<BukkitCommandCompletionContext> {
    val id: String
}
