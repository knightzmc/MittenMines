package me.bristermitten.mittenmines.lang

import me.bristermitten.mittenmines.config.config

data class LangConfig(
    val prefix: String,
    val errors: ErrorLang
) {
    data class ErrorLang(
        val unknownPlayer: LangElement
    )
    companion object {
        val CONFIG = config<LangConfig>("lang.yml")
    }

}
