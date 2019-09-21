package com.sedsoftware.exchange.manager.network.model

data class CurrencyMap(
    val status: CurrencyMapStatus,
    val data: List<CurrencyItem>
)
