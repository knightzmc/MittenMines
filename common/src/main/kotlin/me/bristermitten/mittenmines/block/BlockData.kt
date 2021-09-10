package me.bristermitten.mittenmines.block

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Serializable
data class BlockData(
    @SerialName("blockType") val type: Material,
    val data: Byte? = null,
    @Contextual val bukkitData: Any? = null,
) {
    fun toItemStack(): ItemStack {
        return ItemStack(type, 1, data?.toShort() ?: 0)
    }
}
