package me.bristermitten.mittenmines.lang

import kotlinx.serialization.Serializable
import org.bukkit.Sound

data class LangElement(
    val message: String?,
    val title: String?,
    val subtitle: String?,
    val actionBar: String?,
    val sound: SoundConfig?,
) {
    data class SoundConfig(
        val type: Sound,
        val volume: Float,
        val pitch: Float,
    )
}


