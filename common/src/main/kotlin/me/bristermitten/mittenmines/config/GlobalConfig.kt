package me.bristermitten.mittenmines.config

import me.bristermitten.mittenmines.tax.TaxConfig
import me.bristermitten.mittenmines.tax.global.GlobalTaxConfig

data class GlobalConfig(
    val tax: TaxConfig,
    val globalTax: GlobalTaxConfig,
) {
    companion object {
        val CONFIG = config<GlobalConfig>("config.yml")
    }
}
