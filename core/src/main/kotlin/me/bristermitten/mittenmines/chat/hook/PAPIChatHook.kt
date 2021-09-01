package me.bristermitten.mittenmines.chat.hook

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer

class PAPIChatHook : ChatHook {
    override fun format(message: String, player: OfflinePlayer?): String {
        return PlaceholderAPI.setPlaceholders(player, message)
    }
}
