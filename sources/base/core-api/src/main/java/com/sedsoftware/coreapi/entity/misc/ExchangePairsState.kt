package com.sedsoftware.coreapi.entity.misc

import com.sedsoftware.coreapi.entity.Exchange
import java.util.Date

interface ExchangePairsState {
    val exchange: Exchange
    val syncDate: Date
}
