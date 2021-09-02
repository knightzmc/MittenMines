package me.bristermitten.mittenmines.util

fun <T : Comparable<T>> minMax(a: T, b: T): Pair<T, T> {
    return minOf(a, b) to maxOf(a, b)
}

fun <T : Comparable<T>> minMax(collection: Collection<T>): Pair<T, T> {
    require(collection.isNotEmpty())
    return collection.minOrNull()!! to collection.maxOrNull()!!
}

inline fun <A, T : Comparable<T>> minMax(collection: Collection<A>, selector: (A) -> T): Pair<A, A> {
    require(collection.isNotEmpty())
    return collection.minByOrNull(selector)!! to collection.maxByOrNull(selector)!!
}

