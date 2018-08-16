package com.sedsoftware.core.entity

import java.util.Date

interface ExchangePairsState {
    val exchange: Exchange
    val syncDate: Date
}
