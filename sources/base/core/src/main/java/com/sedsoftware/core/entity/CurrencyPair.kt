package com.sedsoftware.core.entity

interface CurrencyPair {
    val exchange: Exchange
    val baseCurrency: Currency
    val marketCurrency: Currency
    val symbol: String
}
