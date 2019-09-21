package com.sedsoftware.exchange.coinmarketcap.network.model

data class CurrencyMap(
    val status: CurrencyMapStatus,
    val data: List<CurrencyItem>
)
