package com.sedsoftware.core.tests

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import com.github.kittinunf.fuel.moshi.moshiDeserializerOf
import org.spekframework.spek2.dsl.Root

suspend fun <T : Any> Root.get(url: String, resultClass: Class<T>): T? {
    val (_, _, result) = Fuel.get(url).awaitObjectResponseResult(moshiDeserializerOf(resultClass))
    return result.component1()
}
