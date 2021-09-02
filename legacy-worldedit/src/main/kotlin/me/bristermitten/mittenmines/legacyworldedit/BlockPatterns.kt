package me.bristermitten.mittenmines.legacyworldedit

import com.sk89q.worldedit.blocks.BaseBlock
import com.sk89q.worldedit.function.pattern.Pattern
import com.sk89q.worldedit.function.pattern.RandomPattern
import me.bristermitten.mittenmines.block.BlockData
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.block.ConstantBlockPattern
import me.bristermitten.mittenmines.block.RandomBlockPattern
import com.sk89q.worldedit.function.pattern.BlockPattern as WEBlockPattern

@Suppress("DEPRECATION")
fun BlockData.toWEPattern() = WEBlockPattern(BaseBlock(this.type.id, this.data?.toInt() ?: 0))

fun BlockPattern.toWEPattern(): Pattern = when (this) {
    is ConstantBlockPattern -> data.toWEPattern()
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
