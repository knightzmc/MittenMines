package me.bristermitten.mittenmines.chat.hook

import org.bukkit.ChatColor
import org.bukkit.OfflinePlayer

/**
 * This is a truly outrageous hack
 * Essentially, when a papi expansion returns a hex color, it doesn't get parsed properly by MiniMessage
 * and instead it just takes it to the nearest legacy color
 * This class tries to parse the §x§a... hex format that [net.md_5.bungee.api.ChatColor] uses
 * and turns it into a format that minimessage can recognise
 */
class HexColorFixerHook : ChatHook {
    override fun format(message: String, player: OfflinePlayer?): String {
        return HEX_PATTERN.replace(message) { result ->
            val stripped = result.groupValues[1].replace("" + ChatColor.COLOR_CHAR, "")
            "<#$stripped>"
        }
    }

    companion object {
        private val HEX_PATTERN = Regex("§x((§[0-9a-f]){6})")
    }
}
