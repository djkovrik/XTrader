package com.sedsoftware.binance.entity

import com.sedsoftware.coreentity.Currency
import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.Exchange

data class BinanceCurrencyPair(
    override val exchange: Exchange,
    override val baseCurrency: Currency,
    override val marketCurrency: Currency,
    override val symbol: String
) : CurrencyPair
