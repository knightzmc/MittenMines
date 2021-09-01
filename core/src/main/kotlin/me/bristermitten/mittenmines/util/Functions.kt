package me.bristermitten.mittenmines.util

fun <T, R, R2> ((T) -> R).andThen(g: (R) -> R2) = { t: T -> g(this(t)) }
