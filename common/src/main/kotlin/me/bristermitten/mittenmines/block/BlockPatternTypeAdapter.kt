package me.bristermitten.mittenmines.block

import com.google.gson.TypeAdapterFactory
import me.bristermitten.mittenmines.serialization.gson.RuntimeTypeAdapterFactory
import javax.inject.Provider

class BlockPatternTypeAdapter : Provider<TypeAdapterFactory> {
    private val types = mutableSetOf(
        ConstantBlockPattern::class.java,
        RandomBlockPattern::class.java
    )

    fun register(type: Class<out BlockPattern>) {
        types += type
    }

    override fun get(): TypeAdapterFactory {
        val factory = RuntimeTypeAdapterFactory.of(BlockPattern::class.java)
        types.forEach {
            factory.registerSubtype(it)
        }
        return factory
    }
}
