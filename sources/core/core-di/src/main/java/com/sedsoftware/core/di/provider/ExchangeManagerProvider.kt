package com.sedsoftware.core.di.provider

import com.sedsoftware.core.domain.entity.Exchange

interface ExchangeManagerProvider {
    fun provideSupportedExchanges(): Set<Exchange>
}
