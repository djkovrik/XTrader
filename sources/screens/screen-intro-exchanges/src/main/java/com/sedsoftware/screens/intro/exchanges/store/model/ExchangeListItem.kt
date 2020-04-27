package com.sedsoftware.screens.intro.exchanges.store.model

import com.sedsoftware.core.domain.entity.Exchange

data class ExchangeListItem(val exchange: Exchange, val state: DownloadState)
