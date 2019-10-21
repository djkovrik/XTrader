package com.sedsoftware.screens.intro.exchanges.model

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.type.DownloadState

data class ExchangeListItem(val exchange: Exchange, val state: DownloadState)
