package com.sedsoftware.core.di.provider

import com.sedsoftware.core.entity.Exchange

interface ExchangeManagerProvider {
    fun provideSupportedExchanges(): Set<Exchange>
}
