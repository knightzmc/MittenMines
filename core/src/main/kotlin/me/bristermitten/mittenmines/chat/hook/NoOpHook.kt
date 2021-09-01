package me.bristermitten.mittenmines.chat.hook

import org.bukkit.OfflinePlayer

object NoOpHook: ChatHook {
    override fun format(message: String, player: OfflinePlayer?) = message
}
