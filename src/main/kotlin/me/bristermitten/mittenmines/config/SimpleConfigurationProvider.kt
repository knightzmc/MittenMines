package me.bristermitten.mittenmines.config

class SimpleConfigurationProvider<T>(val config: Configuration<T>) :ConfigurationProvider<T> {
    override fun addInvalidationHook(onInvalidation: (T) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun get(): T {
        TODO("Not yet implemented")
    }
}
