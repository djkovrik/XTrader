package com.sedsoftware.exchange.manager.network.model

import org.threeten.bp.OffsetDateTime

data class CurrencyMapStatus(
    val timestamp: OffsetDateTime,
    val error_code: Int
)
