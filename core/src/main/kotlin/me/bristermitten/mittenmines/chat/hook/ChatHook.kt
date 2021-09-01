package me.bristermitten.mittenmines.chat.hook

import org.bukkit.OfflinePlayer

fun interface ChatHook {
    fun format(message: String, player: OfflinePlayer?): String
}
