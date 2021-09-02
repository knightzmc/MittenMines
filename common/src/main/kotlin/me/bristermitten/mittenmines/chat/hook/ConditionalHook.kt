package me.bristermitten.mittenmines.chat.hook

import org.bukkit.OfflinePlayer

class ConditionalHook(private val condition: () -> Boolean,
private val hook: ChatHook) : ChatHook {
    override fun format(message: String, player: OfflinePlayer?): String {
        if(condition()) {
            return hook.format(message, player)
        }
        return NoOpHook.format(message, player)
    }
}
