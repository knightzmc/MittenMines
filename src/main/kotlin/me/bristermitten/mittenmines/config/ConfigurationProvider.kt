package me.bristermitten.mittenmines.config

import javax.inject.Provider

interface ConfigurationProvider<T> : Provider<T> {
    fun addInvalidationHook(onInvalidation: (T) -> Unit)
}
