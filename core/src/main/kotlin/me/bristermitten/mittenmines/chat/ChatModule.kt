package me.bristermitten.mittenmines.chat

import dev.misfitlabs.kotlinguice4.KotlinModule
import dev.misfitlabs.kotlinguice4.multibindings.KotlinMultibinder
import me.bristermitten.mittenmines.chat.hook.ChatHook
import me.bristermitten.mittenmines.chat.hook.HexColorFixerHook
import me.bristermitten.mittenmines.chat.hook.PAPIChatHook
import me.bristermitten.mittenmines.module.MinesModule
import net.kyori.adventure.platform.bukkit.BukkitAudiences

object ChatModule : MinesModule, KotlinModule() {
    override val id = "chat"
    override val guiceModule = this
    override fun configure() {
        bind<ChatFormatter>().to<MiniMessageFormatter>()
        bind<BukkitAudiences>().toProvider<AdventureAudienceProvider>()

        val multibinder = KotlinMultibinder.newSetBinder<ChatHook>(binder())
        multibinder.addBinding().toInstance(PAPIChatHook)
        multibinder.addBinding().to<HexColorFixerHook>()
    }
}
