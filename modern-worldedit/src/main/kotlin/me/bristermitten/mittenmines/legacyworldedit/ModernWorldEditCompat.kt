package me.bristermitten.mittenmines.legacyworldedit

import me.bristermitten.mittenmines.compat.VersionCompat

object ModernWorldEditCompat : VersionCompat {
    override val blockPlacer = ModernBlockPlacer
}
