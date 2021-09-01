package me.bristermitten.mittenmines.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.PaperCommandManager
import me.bristermitten.mittenmines.commands.args.ArgumentCondition
import me.bristermitten.mittenmines.commands.args.ArgumentProcessor
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Provider

class CommandManagerProvider @Inject constructor(
    private val plugin: Plugin,
    private val commands: Set<BaseCommand>,
    private val argumentProcessors: Set<ArgumentProcessor<*>>,
    private val argumentConditions: Set<ArgumentCondition<*>>,
) : Provider<PaperCommandManager> {

    private fun <T> registerProcessor(commandManager: PaperCommandManager, processor: ArgumentProcessor<T>) {
        commandManager.commandContexts.registerContext(processor.type, processor)
        commandManager.commandCompletions.registerAsyncCompletion(processor.id, processor)
        commandManager.commandCompletions.setDefaultCompletion(processor.id, processor.type)
    }

    private fun <T> registerCondition(commandManager: PaperCommandManager, condition: ArgumentCondition<T>) {
        commandManager.commandConditions.addCondition(condition.type, condition.id, condition)
    }

    override fun get(): PaperCommandManager {
        val paperCommandManager = PaperCommandManager(plugin)
        @Suppress("DEPRECATION")
        paperCommandManager.enableUnstableAPI("help")
        argumentProcessors.forEach { argumentProcessor ->
            registerProcessor(paperCommandManager, argumentProcessor)
        }
        argumentConditions.forEach { argumentCondition ->
            registerCondition(paperCommandManager, argumentCondition)
        }
        commands.forEach(paperCommandManager::registerCommand)
        return paperCommandManager
    }
}
