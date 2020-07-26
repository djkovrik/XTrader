package com.sedsoftware.screens.tickers.ui.model

import com.sedsoftware.core.domain.entity.CurrencyPairTick

data class TickerListItem(
    val ticker: CurrencyPairTick,
    val trend: CurrentTrend,
    val updated: String
)
