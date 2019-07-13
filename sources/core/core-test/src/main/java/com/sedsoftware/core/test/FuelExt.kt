package com.sedsoftware.core.test

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Moshi

fun <T : Any> moshiDeserializerOf(clazz: Class<T>, adapter: Any) = object : ResponseDeserializable<T> {

    override fun deserialize(content: String): T? = Moshi.Builder()
        .add(adapter)
        .build()
        .adapter(clazz)
        .fromJson(content)
}
