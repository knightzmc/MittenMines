package me.bristermitten.mittenmines.serialization.gson

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.bristermitten.mittenmines.lang.LangElement
import me.bristermitten.mittenmines.lang.LangElementTypeAdapter
import javax.inject.Provider

class GsonProvider
//@Inject constructor(private val typeAdapterFactories: Set<TypeAdapterFactory>)
    : Provider<Gson> {
    override fun get(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LangElement::class.java, LangElementTypeAdapter)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
//            .also { builder -> emptySet().forEach(builder::registerTypeAdapterFactory) }
            .create()
    }
}
