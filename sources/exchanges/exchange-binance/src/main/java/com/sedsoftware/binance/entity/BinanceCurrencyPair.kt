package com.sedsoftware.binance.entity

import com.sedsoftware.coreapi.entity.Currency
import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.entity.Exchange

data class BinanceCurrencyPair(
    override val exchange: Exchange,
    override val baseCurrency: Currency,
    override val marketCurrency: Currency,
    override val symbol: String
) : CurrencyPair
