package me.bristermitten.mittenmines.util

class Cached<T>(eager: Boolean = false, private val computeWith: () -> T) {
    private var value: T? = null

    constructor(computeWith: () -> T) : this(false, computeWith)

    init {
        if (eager) {
            update()
        }
    }

    private fun update(): T {
        val new = computeWith()
        value = new
        return new
    }

    fun invalidate() {
        value = null
    }

    fun get(): T {
        return value ?: update()
    }
}
