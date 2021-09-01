package me.bristermitten.mittenmines.tax.global

import com.google.inject.Module
import me.bristermitten.mittenmines.module.MinesModule

object GlobalTaxModule : MinesModule {
    override val id = "global-tax"
    override val guiceModule: Module
        get() = TODO("Not yet implemented")
}
