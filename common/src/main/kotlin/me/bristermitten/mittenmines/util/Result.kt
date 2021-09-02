package me.bristermitten.mittenmines.util

sealed interface Result<T>
data class Fail<T>(val exception: OperationFailedException) : Result<T>
data class Success<T>(val value: T) : Result<T>

fun <T> fail(operationFailedException: OperationFailedException) = Fail<T>(operationFailedException)
fun <T> ok(value: T) = Success(value)
