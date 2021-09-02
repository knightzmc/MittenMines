package me.bristermitten.mittenmines.menu

import dev.triumphteam.gui.components.util.ItemNbt
import dev.triumphteam.gui.guis.Gui
import dev.triumphteam.gui.guis.GuiItem
import dev.triumphteam.gui.guis.PaginatedGui
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.block.BlockPatternFactory
import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.mine.MineManager
import net.kyori.adventure.text.Component
import org.bukkit.event.inventory.InventoryAction
import javax.inject.Inject

class BlocksMenu @Inject constructor(
    private val blockPatternFactory: BlockPatternFactory,
    private val mineManager: MineManager,
) {
    fun create(mine: Mine, blockPattern: BlockPattern = mine.pattern): PaginatedGui {
        val gui = Gui.paginated()
            .enableAllInteractions()
            .rows(3)
            .title(Component.text(mine.name ?: mine.id.toString()))
            .create()

        gui.setCloseGuiAction {
            val newPattern = blockPatternFactory.createPattern(gui.inventory.filterNotNull())
            if (newPattern != blockPattern) {
                mineManager.setPattern(mine, newPattern)
            }
        }
        gui.setDefaultClickAction {
            if (it.clickedInventory != it.inventory) {
                return@setDefaultClickAction
            }
            when (it.action) {
                InventoryAction.PLACE_ALL, InventoryAction.PLACE_SOME, InventoryAction.PLACE_ONE -> return@setDefaultClickAction
                InventoryAction.MOVE_TO_OTHER_INVENTORY -> {
                    it.currentItem = ItemNbt.removeTag(it.currentItem, "mf-gui")
                    return@setDefaultClickAction
                }
                else -> {
                    it.isCancelled = true
                    it.currentItem.amount = when (it.action) {
                        InventoryAction.PICKUP_ALL -> it.currentItem.amount + 1
                        InventoryAction.PICKUP_HALF -> it.currentItem.amount - 1
                        else -> {
                            it.currentItem.amount
                        }
                    }.coerceAtMost(it.currentItem.maxStackSize).coerceAtLeast(1)
                }
            }
        }
        blockPattern.getItems().groupingBy { it }
            .eachCount()
            .map { it.key.toItemStack().apply { amount = it.value } }
            .forEach {
                gui.addItem(GuiItem(it, null))
            }
        gui.addItem()
        return gui
    }
}
