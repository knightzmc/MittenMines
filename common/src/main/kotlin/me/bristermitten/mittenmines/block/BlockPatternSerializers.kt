package me.bristermitten.mittenmines.block

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import javax.inject.Provider

class BlockPatternSerializers : Provider<SerializersModule> {

    override fun get(): SerializersModule {
        return SerializersModule {
            polymorphic(BlockPattern::class) {
                subclass(ConstantBlockPattern::class)
                subclass(RandomBlockPattern::class)
            }
        }
    }
}
