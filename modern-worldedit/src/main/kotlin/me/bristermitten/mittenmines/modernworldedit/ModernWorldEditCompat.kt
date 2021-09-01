package me.bristermitten.mittenmines.modernworldedit

import me.bristermitten.mittenmines.compat.VersionCompat

object ModernWorldEditCompat : VersionCompat {
    override val blockPlacer = ModernBlockPlacer
    override val regionSelection = ModernRegionSelection
    override val blockDataFactory = ModernBlockDataFactory
}
