package me.bristermitten.mittenmines.commands.args

import co.aikar.commands.BukkitCommandExecutionContext
import co.aikar.commands.contexts.ContextResolver

interface ArgumentContext<T> : ContextResolver<T, BukkitCommandExecutionContext> {
    val type: Class<T>
}
