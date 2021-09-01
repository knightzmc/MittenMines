package me.bristermitten.mittenmines.compat.vanilla

import me.bristermitten.mittenmines.compat.VersionCompat

object VanillaCompat : VersionCompat {
    override val blockPlacer = BukkitBlockPlacer
}
