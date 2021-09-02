package me.bristermitten.mittenmines.lang

import me.bristermitten.mittenmines.chat.ChatFormatter
import me.bristermitten.mittenmines.util.andThen
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.title.Title
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import javax.inject.Inject
import javax.inject.Provider

class LangService @Inject constructor(
    private val formatter: ChatFormatter,
    private val configProvider: Provider<LangConfig>,
    private val bukkitAudiences: BukkitAudiences,
) {

    private fun send(receiver: CommandSender, message: LangElement, placeholders: Map<String, Any>) {
        val applyPlaceholders = { str: String ->
            placeholders.entries.fold(str) { s, pattern -> s.replace(pattern.key, pattern.value.toString()) }
        }
        if (message.message != null) {
            val replaced = applyPlaceholders(message.message)
            sendMessage(receiver, replaced)
        }
        if (message.title != null || message.subtitle != null) {
            val replacedTitle = message.title?.let(applyPlaceholders) ?: ""
            val replacedSubtitle = message.subtitle?.let(applyPlaceholders) ?: ""
            sendTitle(receiver, replacedTitle, replacedSubtitle)
        }

        if (message.actionBar != null) {
            sendActionBar(receiver, message.actionBar)
        }
        if (message.sound != null) {
            (receiver as? Player)?.let { player ->
                player.playSound(player.location, message.sound.type, message.sound.volume, message.sound.pitch)
            }
        }
    }

    fun sendMessage(
        receiver: CommandSender,
        placeholders: Map<String, Any> = emptyMap(),
        message: (LangConfig) -> String,
    ) {
        val toLangElement = { s: String -> LangElement(s, null, null, null, null) }
        send(receiver, placeholders, message.andThen(toLangElement))
    }

    fun send(
        receiver: CommandSender,
        message: (LangConfig) -> LangElement,
    ) = send(receiver, emptyMap(), message)

    fun send(
        receiver: CommandSender,
        placeholders: Map<String, Any> = emptyMap(),
        message: (LangConfig) -> LangElement,
    ) {
        send(receiver, message(configProvider.get()), placeholders)
    }

    private fun sendMessage(receiver: CommandSender, message: String) {
        bukkitAudiences.sender(receiver)
            .sendMessage(formatter.format(message, receiver as? OfflinePlayer))
    }

    private fun sendActionBar(receiver: CommandSender, bar: String) {
        bukkitAudiences.sender(receiver)
            .sendActionBar(formatter.format(bar, receiver as? OfflinePlayer))
    }

    private fun sendTitle(receiver: CommandSender, title: String, subtitle: String) {
        val formattedTitle = formatter.format(title, receiver as? OfflinePlayer)
        val formattedSubtitle = formatter.format(subtitle, receiver as? OfflinePlayer)
        bukkitAudiences.sender(receiver)
            .showTitle(Title.title(formattedTitle, formattedSubtitle))
    }
}
