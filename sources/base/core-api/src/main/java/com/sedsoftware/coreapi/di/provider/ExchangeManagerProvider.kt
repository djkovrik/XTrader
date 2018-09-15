package com.sedsoftware.coreapi.di.provider

import com.sedsoftware.coreapi.entity.Exchange

interface ExchangeManagerProvider {
    fun provideSupportedExchanges(): Set<Exchange>
}
