package com.sedsoftware.screens.intro.model

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.utils.enums.DownloadState
import com.sedsoftware.core.utils.enums.DownloadState.AVAILABLE

data class ExchangeItem(
    val exchange: Exchange,
    var status: DownloadState = AVAILABLE
)
