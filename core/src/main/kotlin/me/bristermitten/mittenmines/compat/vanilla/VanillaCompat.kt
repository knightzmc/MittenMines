package me.bristermitten.mittenmines.compat.vanilla

import me.bristermitten.mittenmines.compat.RegionSelection
import me.bristermitten.mittenmines.compat.VersionCompat

object VanillaCompat : VersionCompat {
    override val blockPlacer = BukkitBlockPlacer
    override val regionSelection: RegionSelection
        get() = TODO("Not yet implemented")
}
