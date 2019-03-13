package com.sedsoftware.core.tests

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import com.github.kittinunf.fuel.moshi.moshiDeserializerOf
import org.spekframework.spek2.style.specification.Suite

suspend inline fun <reified T : Any> Suite.get(url: String, clazz: Class<T>): T? {
    val (request, response, result) = Fuel.get(url).awaitObjectResponseResult(moshiDeserializerOf(clazz))
    return result.component1()
}
