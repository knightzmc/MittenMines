package me.bristermitten.mittenmines.privatemines

import me.bristermitten.mittenmines.config.config

data class TemplatesConfig(
    val templates: Map<String, TemplateConfig>,
) {
    companion object {
        val CONFIG = config<TemplatesConfig>("templates.yml")
    }

    data class TemplateConfig(
        val schematic: String,
        val permission: String,
        val price: Double,
    )
}
