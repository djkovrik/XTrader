package com.sedsoftware.exchange.binance.entity

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.info.CurrencyPairDepth

data class BinanceCurrencyPairDepth(
    override val pair: CurrencyPair,
    override val amount: Float,
    override val price: Float,
    override val total: Float,
    override val isBid: Boolean
) : CurrencyPairDepth
