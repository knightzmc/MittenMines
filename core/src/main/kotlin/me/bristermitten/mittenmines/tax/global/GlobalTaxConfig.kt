package me.bristermitten.mittenmines.tax.global

data class GlobalTaxConfig(
    val enabled: Boolean,
    val rate: Float,
    val method: GlobalTaxMethod,
) {
    enum class GlobalTaxMethod {
        COMBINE,
        OVERRIDE,
        IGNORE,
    }

}

