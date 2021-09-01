package me.bristermitten.mittenmines

import me.bristermitten.mittenmines.config.ConfigModule
import me.bristermitten.mittenmines.lang.LangConfig
import me.bristermitten.mittenmines.module.ModuleManager
import me.bristermitten.mittenmines.serialization.SerializationModule
import me.bristermitten.mittenmines.tax.TaxModule
import me.bristermitten.mittenmines.tax.global.GlobalTaxModule
import org.bukkit.plugin.java.JavaPlugin

class MittenMines : JavaPlugin() {
    private val configs = setOf(
        LangConfig.CONFIG
    )
    private val modules = setOf(
        TaxModule,
        GlobalTaxModule,
        SerializationModule,
        ConfigModule(configs)
    )

    override fun onEnable() {
        val injector = ModuleManager(modules).makeInjector()
    }
}