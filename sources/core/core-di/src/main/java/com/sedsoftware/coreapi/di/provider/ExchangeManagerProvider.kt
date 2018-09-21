package com.sedsoftware.coreapi.di.provider

import com.sedsoftware.coreentity.Exchange

interface ExchangeManagerProvider {
    fun provideSupportedExchanges(): Set<Exchange>
}
