package com.sedsoftware.core.domain.utils

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange

fun emptyCurrency(): Currency =
    object : Currency {
        override val name: String = ""
        override val label: String = ""
    }

fun emptyExchange(): Exchange =
    object : Exchange {
        override val name: String = ""
        override val label: String = ""
    }
