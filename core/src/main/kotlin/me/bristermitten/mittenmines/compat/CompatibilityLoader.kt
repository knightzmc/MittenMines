package me.bristermitten.mittenmines.compat

import me.bristermitten.mittenmines.compat.vanilla.VanillaCompat
import me.bristermitten.mittenmines.legacyworldedit.LegacyWorldEditCompat
import me.bristermitten.mittenmines.modernworldedit.ModernWorldEditCompat
import org.bukkit.Bukkit
import javax.inject.Provider

class CompatibilityLoader : Provider<VersionCompat> {
    override fun get(): VersionCompat {

        val worldEditVersion = Bukkit.getPluginManager().getPlugin("WorldEdit")?.description?.version
        return when {
            worldEditVersion == null -> VanillaCompat
            worldEditVersion.contains("6.") -> LegacyWorldEditCompat
            worldEditVersion.contains("7.") -> ModernWorldEditCompat
            else -> throw IllegalStateException("Unknown WorldEdit Version")
        }
    }
}
