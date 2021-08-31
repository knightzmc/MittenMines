package me.bristermitten.mittenmines

import me.bristermitten.mittenmines.module.ModuleManager
import me.bristermitten.mittenmines.tax.TaxModule
import me.bristermitten.mittenmines.tax.global.GlobalTaxModule
import org.bukkit.plugin.java.JavaPlugin

class MittenMines : JavaPlugin() {
    private val modules = setOf(
        TaxModule,
        GlobalTaxModule
    )

    override fun onEnable() {
        val injector = ModuleManager(modules).makeInjector()
    }
}
