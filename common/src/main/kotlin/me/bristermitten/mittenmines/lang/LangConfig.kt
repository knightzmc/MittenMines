package me.bristermitten.mittenmines.lang

import me.bristermitten.mittenmines.config.config

data class LangConfig(
    val prefix: String,
    val errors: ErrorLang,
    val commands: CommandLang,
) {
    data class ErrorLang(
        val unknownPlayer: LangElement,
        val unknownMine: LangElement,
        val mineNameExists: LangElement,
    )

    data class CommandLang(
        val pos1Reset: LangElement,
        val pos1Set: LangElement,
        val pos2Reset: LangElement,
        val pos2Set: LangElement,
        val mineRenamed: LangElement,
        val mineDeleted: LangElement
    )

    companion object {
        val CONFIG = config<LangConfig>("lang.yml")
    }

}
