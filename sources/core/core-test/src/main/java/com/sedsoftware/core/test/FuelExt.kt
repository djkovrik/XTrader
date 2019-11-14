package com.sedsoftware.core.test

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun <T : Any> moshiDeserializerOf(clazz: Class<T>, adapter: Any) = object : ResponseDeserializable<T> {

    override fun deserialize(content: String): T? = Moshi.Builder()
        .add(adapter)
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(clazz)
        .fromJson(content)
}
