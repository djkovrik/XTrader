package com.sedsoftware.exchange.binance

import org.spekframework.spek2.style.specification.Suite

fun Suite.readStringFromResource(filePath: String): String {
    val inputStream = this.javaClass.getResourceAsStream(filePath)
    return inputStream?.bufferedReader().use { it?.readText() } ?: ""
}
