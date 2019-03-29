package com.sedsoftware.core.domain.provider

import com.sedsoftware.core.domain.entity.Exchange

interface AssetsProvider {
    fun provideLogo(exchange: Exchange): Int
}
