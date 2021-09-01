package me.bristermitten.mittenmines.compat.vanilla

import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.compat.VersionCompat
import me.bristermitten.mittenmines.modernworldedit.ModernBlockDataFactory

object VanillaCompat : VersionCompat {
    override val blockPlacer = BukkitBlockPlacer
    override val regionSelection: RegionSelection
        get() = TODO("Not yet implemented")
    override val blockDataFactory = ModernBlockDataFactory
}
