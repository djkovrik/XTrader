package com.sedsoftware.binance.common

import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.Exchange
import com.sedsoftware.core.entity.info.CurrencyPairTick

data class BinanceCurrencyPairTick(
    override val pair: CurrencyPair,
    override val exchange: Exchange,
    override val bid: Float,
    override val ask: Float,
    override val last: Float
) : CurrencyPairTick
