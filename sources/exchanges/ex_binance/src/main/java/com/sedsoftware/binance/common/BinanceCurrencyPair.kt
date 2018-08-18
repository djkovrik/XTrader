package com.sedsoftware.binance.common

import com.sedsoftware.core.entity.Currency
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.Exchange

data class BinanceCurrencyPair(
    override val exchange: Exchange,
    override val baseCurrency: Currency,
    override val marketCurrency: Currency,
    override val symbol: String
) : CurrencyPair
