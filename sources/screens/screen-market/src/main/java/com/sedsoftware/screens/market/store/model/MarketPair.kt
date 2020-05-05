package com.sedsoftware.screens.market.store.model

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange

data class MarketPair(val base: Currency, val market: Currency, val exchange: Exchange)
