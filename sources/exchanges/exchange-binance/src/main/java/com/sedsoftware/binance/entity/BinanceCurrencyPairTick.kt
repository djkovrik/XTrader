package com.sedsoftware.binance.entity

import com.sedsoftware.coreapi.entity.Exchange
import com.sedsoftware.coreapi.entity.info.CurrencyPairTick

data class BinanceCurrencyPairTick(
    override val exchange: Exchange,
    override val symbol: String,
    override val bidPrice: Float,
    override val bidQuantity: Float,
    override val askPrice: Float,
    override val askQuantity: Float
) : CurrencyPairTick
