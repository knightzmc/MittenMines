package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.compat.vanilla.DelegatingRegionSelector
import me.bristermitten.mittenmines.compat.vanilla.VanillaCompat
import me.bristermitten.mittenmines.legacyworldedit.LegacyWorldEditCompat
import me.bristermitten.mittenmines.modernworldedit.ModernWorldEditCompat
import org.bukkit.Bukkit
import javax.inject.Inject
import javax.inject.Provider

class CompatibilityLoader @Inject constructor(private val vanillaProvider: Provider<VanillaCompat>) :
    Provider<VersionCompat> {
    override fun get(): VersionCompat {

        val worldEditVersion = Bukkit.getPluginManager().getPlugin("WorldEdit")?.description?.version
            ?: return vanillaProvider.get()
        val weCompat = when {
            worldEditVersion.contains("6.") -> LegacyWorldEditCompat
            worldEditVersion.contains("7.") -> ModernWorldEditCompat
            else -> throw IllegalStateException("Unknown WorldEdit Version")
        }
        // This is so that the vanilla region selector has priority, in case they decide to use that instead of /wand
        return DelegatingVersionCompat.from(weCompat)
            .copy(regionSelection =
            DelegatingRegionSelector(vanillaProvider.get().regionSelection, weCompat.regionSelection))
    }
}
