package me.bristermitten.mittenmines.tax

import dev.misfitlabs.kotlinguice4.KotlinModule
import dev.misfitlabs.kotlinguice4.multibindings.KotlinMultibinder
import me.bristermitten.mittenmines.module.MinesModule
import me.bristermitten.mittenmines.tax.global.GlobalTaxModule
import java.util.*

object TaxModule : MinesModule, KotlinModule() {

    override val id = "tax"
    override val guiceModule = this

    override fun configure() {
        KotlinMultibinder.newSetBinder<EventListener>(binder()).let { multibinder ->
//            multibinder.addBinding().to<>()
        }
    }

    override val children = setOf(GlobalTaxModule)
}
