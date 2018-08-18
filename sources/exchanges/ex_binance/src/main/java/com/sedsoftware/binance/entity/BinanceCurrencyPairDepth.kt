package com.sedsoftware.binance.entity

import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairDepth

data class BinanceCurrencyPairDepth(
    override val pair: CurrencyPair,
    override val amount: Float,
    override val price: Float,
    override val total: Float,
    override val isBid: Boolean
) : CurrencyPairDepth
