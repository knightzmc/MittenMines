package me.bristermitten.mittenmines.commands.args

import co.aikar.commands.BukkitCommandExecutionContext
import co.aikar.commands.BukkitCommandIssuer
import co.aikar.commands.CommandConditions.ParameterCondition

interface ArgumentCondition<T> : ParameterCondition<T, BukkitCommandExecutionContext, BukkitCommandIssuer> {
    val type: Class<T>
    val id: String
}
