package me.bristermitten.mittenmines.module

import com.google.inject.Guice
import com.google.inject.Injector

class ModuleManager(private val modules: Set<MinesModule>) {


    fun makeInjector(): Injector {
        val guiceModules = modules
            .flatMap { it.children + it }
            .map { it.guiceModule }
            .toTypedArray()
        return Guice.createInjector(*guiceModules)
    }
}
