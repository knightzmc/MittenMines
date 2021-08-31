package me.bristermitten.mittenmines.trait

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

interface EventListener : Listener, Registerable {
    override fun register(plugin: Plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }
}
