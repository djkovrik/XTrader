package com.sedsoftware.binance.entity

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairDepth

data class BinanceCurrencyPairDepth(
    override val pair: CurrencyPair,
    override val amount: Float,
    override val price: Float,
    override val total: Float,
    override val isBid: Boolean
) : CurrencyPairDepth
