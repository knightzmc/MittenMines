package me.bristermitten.mittenmines.module

import com.google.inject.Module

interface MinesModule {
    val id: String

    val guiceModule: Module

    val children: Set<MinesModule>
        get() = emptySet()
}
