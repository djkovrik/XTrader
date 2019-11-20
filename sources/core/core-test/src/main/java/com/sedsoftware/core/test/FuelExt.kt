package com.sedsoftware.core.test

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type


fun <T : Any> moshiDeserializerOf(clazz: Class<T>, adapter: Any) =
    object : ResponseDeserializable<T> {

    override fun deserialize(content: String): T? = Moshi.Builder()
        .add(adapter)
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(clazz)
        .fromJson(content)
}


fun moshiDeserializerOfStringList() =
    object : ResponseDeserializable<List<String>> {

        var listType: Type = Types.newParameterizedType(List::class.java, String::class.java)

        override fun deserialize(content: String): List<String>? =
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
                .adapter<List<String>>(listType)
                .fromJson(content)
    }
