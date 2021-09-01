package me.bristermitten.mittenmines.compat.vanilla

import me.bristermitten.mittenmines.compat.VersionCompat
import me.bristermitten.mittenmines.modernworldedit.ModernBlockDataFactory
import javax.inject.Inject

class VanillaCompat @Inject constructor(override val regionSelection: VanillaRegionSelection) :
    VersionCompat {
    override val blockPlacer = BukkitBlockPlacer
    override val blockDataFactory = ModernBlockDataFactory
}
