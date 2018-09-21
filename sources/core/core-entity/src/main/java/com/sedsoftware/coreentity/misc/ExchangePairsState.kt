package com.sedsoftware.coreentity.misc

import com.sedsoftware.coreentity.Exchange
import java.util.Date

interface ExchangePairsState {
    val exchange: Exchange
    val syncDate: Date
}
