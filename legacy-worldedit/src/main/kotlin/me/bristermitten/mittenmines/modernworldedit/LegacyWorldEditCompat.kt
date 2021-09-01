package me.bristermitten.mittenmines.modernworldedit

import me.bristermitten.mittenmines.compat.VersionCompat

object LegacyWorldEditCompat : VersionCompat {
    override val blockPlacer = LegacyBlockPlacer

    override val regionSelection = LegacyRegionSelection
}
