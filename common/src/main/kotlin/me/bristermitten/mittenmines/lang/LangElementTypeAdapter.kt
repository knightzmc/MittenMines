package me.bristermitten.mittenmines.lang

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import org.bukkit.Sound

object LangElementTypeAdapter : TypeAdapter<LangElement>() {
    override fun write(out: JsonWriter, value: LangElement) {
        val (message, title, subtitle, actionBar, sound) = value
        if (sequenceOf(title, subtitle, actionBar, sound).all { it == null }) {
            if (message == null) {
                throw IllegalArgumentException("Empty LangElement!")
            }
            out.value(message)
            return
        }
        out.beginObject()

        out.name("message").value(message)
        out.name("title").value(title)
        out.name("subtitle").value(subtitle)
        out.name("action-bar").value(actionBar)
        if (sound != null) {
            out.name("sound")
            out.beginObject()
            out.name("type").value(sound.type.name)
            out.name("pitch").value(sound.pitch)
            out.name("volume").value(sound.volume)
            out.endObject()
        }
        out.endObject()
    }

    override fun read(reader: JsonReader): LangElement {
        var empty = LangElement(null, null, null, null, null)
        if (reader.peek() == JsonToken.STRING) {
            empty = empty.copy(message = reader.nextString())
            return empty
        }
        reader.beginObject()
        while (reader.hasNext()) {
            empty = when (val name = reader.nextName()) {
                "message" -> empty.copy(message = reader.nextString())
                "title" -> empty.copy(title = reader.nextString())
                "subtitle" -> empty.copy(subtitle = reader.nextString())
                "action-bar" -> empty.copy(actionBar = reader.nextString())
                "sound" -> {
                    var sound = LangElement.SoundConfig(Sound.AMBIENCE_CAVE, 0f, 0f) // the defaults dont really matter
                    reader.beginObject()
                    while (reader.hasNext()) {
                        sound = when (val soundName = reader.nextName()) {
                            "type" -> sound.copy(type = Sound.valueOf(reader.nextString()))
                            "pitch" -> sound.copy(pitch = reader.nextDouble().toFloat())
                            "volume" -> sound.copy(volume = reader.nextDouble().toFloat())
                            else -> throw IllegalArgumentException("Unknown value in sound config $soundName")
                        }
                    }
                    reader.endObject()
                    empty.copy(sound = sound)
                }
                else -> throw IllegalArgumentException("Unknown value in lang element config $name")
            }
        }
        return empty
    }
}
