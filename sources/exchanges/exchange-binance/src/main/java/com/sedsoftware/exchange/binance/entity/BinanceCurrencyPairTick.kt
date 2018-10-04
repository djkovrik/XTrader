package com.sedsoftware.exchange.binance.entity

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.entity.info.CurrencyPairTick

data class BinanceCurrencyPairTick(
    override val exchange: Exchange,
    override val symbol: String,
    override val bidPrice: Float,
    override val bidQuantity: Float,
    override val askPrice: Float,
    override val askQuantity: Float
) : CurrencyPairTick
