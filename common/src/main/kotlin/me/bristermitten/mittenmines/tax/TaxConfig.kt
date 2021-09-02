package me.bristermitten.mittenmines.tax

data class TaxConfig(
    val enabled: Boolean,
    val maxTaxRate: Float,
    val minTaxRate: Float,
)
