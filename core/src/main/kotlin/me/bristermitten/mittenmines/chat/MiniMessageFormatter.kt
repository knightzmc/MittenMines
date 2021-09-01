package me.bristermitten.mittenmines.chat

import me.bristermitten.mittenmines.chat.hook.ChatHook
import net.kyori.adventure.text.Component
import org.bukkit.OfflinePlayer
import javax.inject.Inject

class MiniMessageFormatter @Inject constructor(
    private val miniMessageFactory: MiniMessageFactory,
    private val chatHooks: Set<ChatHook>,
) : ChatFormatter {

    override fun preFormat(message: String, player: OfflinePlayer?): String {
        return chatHooks.fold(message) { m, r -> r.format(m, player) }
    }

    override fun withHooks(vararg hooks: ChatHook): ChatFormatter {
        return MiniMessageFormatter(
            miniMessageFactory,
            chatHooks + hooks
        )
    }

    override fun format(message: String, player: OfflinePlayer?): Component {
        val formatted = preFormat(message, player)
        return miniMessageFactory.create(player)
            .parse(formatted)
    }
}
