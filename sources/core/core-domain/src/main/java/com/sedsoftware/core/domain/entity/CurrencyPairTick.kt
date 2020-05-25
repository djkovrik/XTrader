package com.sedsoftware.core.domain.entity

interface CurrencyPairTick {
    val pair: CurrencyPair
    val price: Float
    val percentChange: Float
    val added: Long
    val refreshed: Long
}
