package me.bristermitten.mittenmines.compat

import com.google.inject.Provides
import dev.misfitlabs.kotlinguice4.KotlinModule
import me.bristermitten.mittenmines.module.MinesModule

object CompatModule : MinesModule, KotlinModule() {
    override val id = "compat"
    override val guiceModule = this

    override fun configure() {
        bind<VersionCompat>().toProvider<CompatibilityLoader>()
    }

    @Provides
    fun provideBlockPlacer(compat: VersionCompat) = compat.blockPlacer

    @Provides
    fun provideRegionSelection(compat: VersionCompat) = compat.regionSelection
}
