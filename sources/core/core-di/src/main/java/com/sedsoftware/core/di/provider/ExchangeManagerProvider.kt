package com.sedsoftware.core.di.provider

import com.sedsoftware.core.domain.entity.Exchange

interface ExchangeManagerProvider : BinanceProvider {
    fun provideSupportedExchanges(): Set<Exchange>
}
