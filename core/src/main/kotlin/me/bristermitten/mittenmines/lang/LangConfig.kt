package me.bristermitten.mittenmines.lang

import me.bristermitten.mittenmines.config.config

data class LangConfig(
    val prefix: String,
) {
    companion object {
        val CONFIG = config<LangConfig>("lang.yml")
    }
}
