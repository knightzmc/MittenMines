package me.bristermitten.mittenmines.chat

import dev.misfitlabs.kotlinguice4.KotlinModule
import dev.misfitlabs.kotlinguice4.multibindings.KotlinMultibinder
import me.bristermitten.mittenmines.chat.hook.ChatHook
import me.bristermitten.mittenmines.chat.hook.HexColorFixerHook
import me.bristermitten.mittenmines.chat.hook.PAPIChatHook

class ChatModule : KotlinModule() {
    override fun configure() {
        bind<ChatFormatter>().to<MiniMessageFormatter>()

        val multibinder = KotlinMultibinder.newSetBinder<ChatHook>(binder())
        multibinder.addBinding().toInstance(PAPIChatHook)
        multibinder.addBinding().to<HexColorFixerHook>()
    }
}
