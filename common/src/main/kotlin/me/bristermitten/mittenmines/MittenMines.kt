package me.bristermitten.mittenmines

import me.bristermitten.mittenmines.chat.ChatModule
import me.bristermitten.mittenmines.commands.CommandsModule
import me.bristermitten.mittenmines.config.ConfigModule
import me.bristermitten.mittenmines.config.GlobalConfig
import me.bristermitten.mittenmines.lang.LangConfig
import me.bristermitten.mittenmines.module.MinesModule
import me.bristermitten.mittenmines.module.ModuleManager
import me.bristermitten.mittenmines.privatemines.TemplatesConfig
import me.bristermitten.mittenmines.serialization.SerializationModule
import me.bristermitten.mittenmines.tax.TaxModule
import me.bristermitten.mittenmines.tax.global.GlobalTaxModule
import org.bukkit.plugin.java.JavaPlugin

class MittenMines : JavaPlugin() {
    private val configs = setOf(
        LangConfig.CONFIG,
        GlobalConfig.CONFIG,
        TemplatesConfig.CONFIG,
    )

    private val modules = setOf(
        CoreModule(this),
        TaxModule,
        ChatModule,
        GlobalTaxModule,
        SerializationModule,
        ConfigModule(configs),
        Class.forName("me.bristermitten.mittenmines.compat.CompatModule").kotlin.objectInstance as MinesModule,
        CommandsModule
    )

    override fun onEnable() {
        val injector = ModuleManager(modules).makeInjector()
    }
}
