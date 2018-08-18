package com.sedsoftware.core.entity.misc

import com.sedsoftware.core.entity.Exchange
import java.util.Date

interface ExchangePairsState {
    val exchange: Exchange
    val syncDate: Date
}
