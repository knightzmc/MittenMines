package me.bristermitten.mittenmines.block

import me.bristermitten.mittenmines.compat.BlockDataFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BlockPatternFactory @Inject constructor(private val blockDataFactory: BlockDataFactory) {
    fun createPattern(items: List<ItemStack>): BlockPattern {
        if (items.size == 1) {
            val head = items.first()
            return ConstantBlockPattern(blockDataFactory.createBlockData(head))
        }

        val dataList = items.flatMap { List(it.amount) { _ -> blockDataFactory.createBlockData(it) } }
        return RandomBlockPattern(dataList)
    }
}
