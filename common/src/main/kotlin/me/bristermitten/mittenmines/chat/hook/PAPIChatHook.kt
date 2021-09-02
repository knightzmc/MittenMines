package me.bristermitten.mittenmines.chat.hook

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Bukkit

val PAPIChatHook = ConditionalHook({
    Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")
}, { message, player ->
    PlaceholderAPI.setPlaceholders(player, message)
})
