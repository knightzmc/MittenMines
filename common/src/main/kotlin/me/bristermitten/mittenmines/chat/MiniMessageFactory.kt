package me.bristermitten.mittenmines.chat

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.OfflinePlayer
import javax.inject.Inject

class MiniMessageFactory @Inject constructor(private val miniMessagePlaceholders: MiniMessagePlaceholders) {
    fun create(player: OfflinePlayer?): MiniMessage {
        val placeholders = miniMessagePlaceholders.get()
        return MiniMessage.builder()
            .placeholderResolver { placeholder ->
                player?.let { placeholders[placeholder]?.invoke(it) }
            }
            .build()
    }
}
