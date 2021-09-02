package me.bristermitten.mittenmines.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.PaperCommandManager
import dev.misfitlabs.kotlinguice4.KotlinModule
import dev.misfitlabs.kotlinguice4.multibindings.KotlinMultibinder
import me.bristermitten.mittenmines.commands.args.ArgumentCondition
import me.bristermitten.mittenmines.commands.args.ArgumentProcessor
import me.bristermitten.mittenmines.commands.args.OfflinePlayerProcessor
import me.bristermitten.mittenmines.module.MinesModule

object CommandsModule : MinesModule, KotlinModule() {
    override val guiceModule = this
    override val id = "commands"

    override fun configure() {
        bind<PaperCommandManager>()
            .toProvider<CommandManagerProvider>()
            .asEagerSingleton()

        val commandBinder = KotlinMultibinder.newSetBinder<BaseCommand>(binder())
        commandBinder.addBinding().to<PrivateMinesCommand>()
        commandBinder.addBinding().to<MinesCommand>()


        val argProcessorBinder = KotlinMultibinder.newSetBinder<ArgumentProcessor<*>>(binder())
        argProcessorBinder.addBinding().to<OfflinePlayerProcessor>()

        val conditionBinder = KotlinMultibinder.newSetBinder<ArgumentCondition<*>>(binder())
    }
}
