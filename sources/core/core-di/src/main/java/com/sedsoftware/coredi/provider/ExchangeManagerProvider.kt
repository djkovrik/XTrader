package com.sedsoftware.coredi.provider

import com.sedsoftware.coreentity.Exchange

interface ExchangeManagerProvider {
    fun provideSupportedExchanges(): Set<Exchange>
}
