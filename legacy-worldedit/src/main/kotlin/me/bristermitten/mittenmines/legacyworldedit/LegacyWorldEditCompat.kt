package me.bristermitten.mittenmines.legacyworldedit

import me.bristermitten.mittenmines.compat.VersionCompat

object LegacyWorldEditCompat : VersionCompat {
    override val blockPlacer = LegacyBlockPlacer

    override val regionSelection = LegacyRegionSelection

    override val blockDataFactory = LegacyBlockDataFactory
}
