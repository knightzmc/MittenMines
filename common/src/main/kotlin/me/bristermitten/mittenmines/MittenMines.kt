package me.bristermitten.mittenmines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.bristermitten.mittenmines.chat.ChatModule
import me.bristermitten.mittenmines.commands.CommandsModule
import me.bristermitten.mittenmines.config.ConfigModule
import me.bristermitten.mittenmines.config.FileWatcherService
import me.bristermitten.mittenmines.config.GlobalConfig
import me.bristermitten.mittenmines.lang.LangConfig
import me.bristermitten.mittenmines.mine.MineModule
import me.bristermitten.mittenmines.mine.storage.MineStorage
import me.bristermitten.mittenmines.module.MinesModule
import me.bristermitten.mittenmines.module.ModuleManager
import me.bristermitten.mittenmines.persistence.PersistenceConfig
import me.bristermitten.mittenmines.privatemines.TemplatesConfig
import me.bristermitten.mittenmines.serialization.SerializationModule
import me.bristermitten.mittenmines.tax.TaxModule
import me.bristermitten.mittenmines.tax.global.GlobalTaxModule
import me.bristermitten.mittenmines.trait.HasPlugin
import me.bristermitten.mittenmines.util.async
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject

class MittenMines : JavaPlugin(), HasPlugin {
    private val configs = setOf(
        LangConfig.CONFIG,
        GlobalConfig.CONFIG,
        TemplatesConfig.CONFIG,
        PersistenceConfig.CONFIG
    )

    private val modules = setOf(
        CoreModule(this),
        TaxModule,
        ChatModule,
        GlobalTaxModule,
        SerializationModule,
        ConfigModule(configs),
        Class.forName("me.bristermitten.mittenmines.compat.CompatModule").getField("INSTANCE").get(null) as MinesModule,
        CommandsModule,
        MineModule
    )

    @Inject
    private lateinit var mineStorage: MineStorage

    @Inject
    private lateinit var fileWatcherService: FileWatcherService

    override fun onEnable() {
        val injector = ModuleManager(modules).makeInjector()
        injector.injectMembers(this)
        fileWatcherService.watch()
        async.launch {
            mineStorage.fetchAll() // Start loading any mines
        }
    }

    override fun onDisable() {
        runBlocking {
            mineStorage.saveAll()
        }

    }

    override val plugin: MittenMines = this
}
