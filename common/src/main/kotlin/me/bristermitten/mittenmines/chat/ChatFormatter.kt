package me.bristermitten.mittenmines.chat

import org.bukkit.OfflinePlayer
import me.bristermitten.mittenmines.chat.hook.ChatHook
import net.kyori.adventure.text.Component

interface ChatFormatter {
    fun format(message: String, player: OfflinePlayer?): Component
    fun preFormat(message: String, player: OfflinePlayer?): String
    fun withHooks(vararg hooks: ChatHook): ChatFormatter
}
