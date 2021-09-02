package me.bristermitten.mittenmines.menu

import dev.triumphteam.gui.guis.Gui
import dev.triumphteam.gui.guis.GuiItem
import dev.triumphteam.gui.guis.PaginatedGui
import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.block.BlockPatternFactory
import me.bristermitten.mittenmines.mine.Mine
import me.bristermitten.mittenmines.mine.MineManager
import net.kyori.adventure.text.Component
import javax.inject.Inject

class BlocksMenu @Inject constructor(
    private val blockPatternFactory: BlockPatternFactory,
    private val mineManager: MineManager,
) {
    fun create(mine: Mine, blockPattern: BlockPattern = mine.pattern): PaginatedGui {
        val gui = Gui.paginated()
            .enableAllInteractions()
            .pageSize(54)
            .title(Component.text(mine.name ?: mine.id.toString()))
            .create()
        gui.setCloseGuiAction {
            mineManager.setPattern(mine, blockPatternFactory.createPattern(gui.inventory.filterNotNull()))
        }
        blockPattern.getItems()
            .forEach {
                gui.addItem(GuiItem(it.toItemStack(), null))
            }

        return gui
    }
}
