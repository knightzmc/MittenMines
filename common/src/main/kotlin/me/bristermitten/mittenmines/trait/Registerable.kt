package me.bristermitten.mittenmines.trait

import org.bukkit.plugin.Plugin

interface Registerable {
    fun register(plugin: Plugin)
}
