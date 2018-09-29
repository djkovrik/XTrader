package com.sedsoftware.core.domain.entity.misc

import com.sedsoftware.core.domain.entity.Exchange
import java.util.Date

interface ExchangePairsState {
    val exchange: Exchange
    val syncDate: Date
}
