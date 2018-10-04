package com.sedsoftware.exchange.binance.entity

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.Exchange

data class BinanceCurrencyPair(
    override val exchange: Exchange,
    override val baseCurrency: Currency,
    override val marketCurrency: Currency,
    override val symbol: String
) : CurrencyPair
