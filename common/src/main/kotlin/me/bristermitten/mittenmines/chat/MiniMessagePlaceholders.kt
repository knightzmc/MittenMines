package me.bristermitten.mittenmines.chat

import net.kyori.adventure.text.ComponentLike
import org.bukkit.OfflinePlayer
import javax.inject.Inject
import javax.inject.Provider

class MiniMessagePlaceholders @Inject constructor(private val chatFormatter: ChatFormatter) :
    Provider<Map<String, (OfflinePlayer) -> ComponentLike>> {

    override fun get(): Map<String, (OfflinePlayer) -> ComponentLike> {
        return HashMap()
    }
}
