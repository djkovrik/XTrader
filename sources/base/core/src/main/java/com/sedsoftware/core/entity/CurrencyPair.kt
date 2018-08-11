package com.sedsoftware.core.entity

interface CurrencyPair {
    val baseCurrency: Currency
    val marketCurrency: Currency
    val symbol: String
}
