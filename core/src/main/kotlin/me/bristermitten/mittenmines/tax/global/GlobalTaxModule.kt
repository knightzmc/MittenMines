package me.bristermitten.mittenmines.tax.global

import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.module.MinesModule

object GlobalTaxModule : MinesModule, KotlinModule() {
    override val id = "global-tax"
    override val guiceModule = this
}
