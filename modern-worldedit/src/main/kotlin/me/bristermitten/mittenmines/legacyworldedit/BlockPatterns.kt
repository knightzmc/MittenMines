package me.bristermitten.mittenmines.legacyworldedit

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.function.pattern.Pattern
import com.sk89q.worldedit.world.block.BlockState
import me.bristermitten.mittenmines.block.BlockData
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.block.ConstantBlockPattern
import com.sk89q.worldedit.function.pattern.BlockPattern as WEBlockPattern

fun BlockData.toBaseBlock(): BlockState {
    // We ignore the data on modern versions
    return BukkitAdapter.adapt(this.bukkitData as org.bukkit.block.data.BlockData)
}

@Suppress("DEPRECATION")
fun BlockPattern.toWEPattern(): Pattern = when (this) {
    is ConstantBlockPattern -> WEBlockPattern(this.data.toBaseBlock().toBaseBlock())
    else -> throw UnsupportedOperationException("Cannot convert $this to WorldEdit Pattern")
}
