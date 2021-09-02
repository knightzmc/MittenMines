package me.bristermitten.mittenmines.util

fun <T: Comparable<T>> minMax(a: T, b: T) : Pair<T, T> {
    return minOf(a, b) to maxOf(a, b)
}
