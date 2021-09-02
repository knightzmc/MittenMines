package me.bristermitten.mittenmines.modernworldedit

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.function.pattern.Pattern
import com.sk89q.worldedit.function.pattern.RandomPattern
import com.sk89q.worldedit.world.block.BlockState
import me.bristermitten.mittenmines.block.BlockData
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.block.ConstantBlockPattern
import me.bristermitten.mittenmines.block.RandomBlockPattern
import com.sk89q.worldedit.function.pattern.BlockPattern as WEBlockPattern

fun BlockData.toBaseBlock(): BlockState {
    // We ignore the data on modern versions
    return BukkitAdapter.adapt(this.bukkitData as org.bukkit.block.data.BlockData)
}

fun BlockData.toWEPattern() = WEBlockPattern(toBaseBlock())

@Suppress("DEPRECATION")
fun BlockPattern.toWEPattern(): Pattern = when (this) {
    is ConstantBlockPattern -> this.data.toWEPattern()
    is RandomBlockPattern -> {
        val random = RandomPattern()
        val total = all.size
        all.forEach { data ->
            val proportion = total / all.count { it == data }.toDouble()
            random.add(data.toWEPattern(), proportion)
        }
        random
    }
    else -> throw UnsupportedOperationException("Cannot convert $this to WorldEdit Pattern")
}
