package me.bristermitten.mittenmines.mine

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.util.*

sealed class MineOwner
data class PlayerOwner(val uuid: UUID) : MineOwner()
object ServerOwner : MineOwner()

object MineOwnerTypeAdapter : TypeAdapter<MineOwner>() {
    override fun write(out: JsonWriter, value: MineOwner) {
        when (value) {
            is ServerOwner -> out.value("server")
            is PlayerOwner -> out.value(value.uuid.toString())
        }
    }

    override fun read(`in`: JsonReader): MineOwner {
        return when (val name = `in`.nextString()) {
            "server" -> ServerOwner
            else -> PlayerOwner(UUID.fromString(name))
        }
    }
}
