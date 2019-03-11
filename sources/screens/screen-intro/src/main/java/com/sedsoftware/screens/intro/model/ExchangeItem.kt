package com.sedsoftware.screens.intro.model

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.screens.intro.model.ExchangeStatus.AVAILABLE

data class ExchangeItem(
    val exchange: Exchange,
    var status: ExchangeStatus = AVAILABLE
)
