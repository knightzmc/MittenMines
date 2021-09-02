package me.bristermitten.mittenmines.block

import org.bukkit.Material

data class BlockData(val type: Material, val data: Byte? = null, val bukkitData: Any? = null)
