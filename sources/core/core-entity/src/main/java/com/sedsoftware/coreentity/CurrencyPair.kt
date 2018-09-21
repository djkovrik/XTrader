package com.sedsoftware.coreentity

interface CurrencyPair {
    val exchange: Exchange
    val baseCurrency: Currency
    val marketCurrency: Currency
    val symbol: String
}
