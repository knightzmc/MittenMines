package me.bristermitten.mittenmines.modernworldedit

import com.sk89q.worldedit.blocks.BaseBlock
import com.sk89q.worldedit.function.pattern.Pattern
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.block.ConstantBlockPattern
import com.sk89q.worldedit.function.pattern.BlockPattern as WEBlockPattern

@Suppress("DEPRECATION")
fun BlockPattern.toWEPattern(): Pattern = when (this) {
    is ConstantBlockPattern -> WEBlockPattern(BaseBlock(this.data.type.id, this.data.data?.toInt() ?: 0))
    else -> throw UnsupportedOperationException("Cannot convert $this to WorldEdit Pattern")
}
