package me.bristermitten.mittenmines.compat.vanilla

import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.entity.Region
import me.bristermitten.mittenmines.player.MinesPlayerStorage
import org.bukkit.entity.Player
import javax.inject.Inject

class VanillaRegionSelection @Inject constructor(private val minesPlayerStorage: MinesPlayerStorage) : RegionSelection {
    override fun getSelection(player: Player): Region? {
        val minesPlayer = minesPlayerStorage[player]
        val p1 = minesPlayer.selection1 ?: return null
        val p2 = minesPlayer.selection2 ?: return null
        require(p1.world == p2.world)
        return Region.of(p1.world, p1.toBlockPoint(), p2.toBlockPoint())
    }
}
