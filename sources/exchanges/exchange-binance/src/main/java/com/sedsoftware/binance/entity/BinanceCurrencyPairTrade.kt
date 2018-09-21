package com.sedsoftware.binance.entity

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairTrade

data class BinanceCurrencyPairTrade(
    override val pair: CurrencyPair,
    override val id: Long,
    override val timestamp: Long,
    override val quantity: Float,
    override val price: Float,
    override val total: Float,
    override val isBuy: Boolean
) : CurrencyPairTrade
