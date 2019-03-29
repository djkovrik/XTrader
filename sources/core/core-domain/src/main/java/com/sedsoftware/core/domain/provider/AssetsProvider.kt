package com.sedsoftware.core.domain.provider

import com.sedsoftware.core.domain.entity.Exchange

interface AssetsProvider {
    fun getLogoResource(exchange: Exchange): Int
}
