package me.bristermitten.mittenmines.util

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

// Credit: https://stackoverflow.com/a/49418496/6272977
class CompositeType(private val baseClass: Class<*>, vararg genericClasses: Class<*>) : ParameterizedType {
    private val name: String
    override fun getTypeName(): String {
        return name
    }

    private val genericClass: Array<Type>

    override fun getActualTypeArguments() = genericClass

    override fun getRawType() =
        baseClass

    override fun getOwnerType() = null


    init {
        genericClass = genericClasses.map { it }.toTypedArray() // what?
        val generics: List<String> = genericClasses.asList()
            .map { obj -> obj.name }
        val genericTypeString: String = generics.joinToString(",")
        name = baseClass.name + "<" + genericTypeString + ">"
    }
}
