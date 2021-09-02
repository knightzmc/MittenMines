package me.bristermitten.mittenmines.util

import java.lang.reflect.ParameterizedType

// Credit: https://stackoverflow.com/a/49418496/6272977
class CompositeType(private val baseClass: Class<*>, private vararg val genericClasses: Class<*>) : ParameterizedType {
    private val name: String
    override fun getTypeName(): String {
        return name
    }

    override fun getActualTypeArguments() = genericClasses

    override fun getRawType() =
        baseClass

    override fun getOwnerType() = null


    init {
        // what?
        val generics: List<String> = genericClasses.asList()
            .map { obj -> obj.name }
        val genericTypeString: String = generics.joinToString(",")
        name = baseClass.name + "<" + genericTypeString + ">"
    }
}
