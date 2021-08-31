package me.bristermitten.mittenmines.config

data class Configuration<T>(
    val fileName: String,
    val type: Class<T>,
)

inline fun <reified T> config(fileName: String) =
    Configuration(fileName, T::class.java)
