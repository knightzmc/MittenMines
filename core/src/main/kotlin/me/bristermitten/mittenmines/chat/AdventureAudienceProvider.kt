package me.bristermitten.mittenmines.chat

import me.bristermitten.mittenmines.trait.HasPlugin
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Provider

class AdventureAudienceProvider @Inject constructor(private val plugin: Plugin): Provider<BukkitAudiences> {
    override fun get(): BukkitAudiences {
        return BukkitAudiences.create(plugin)
    }
}
