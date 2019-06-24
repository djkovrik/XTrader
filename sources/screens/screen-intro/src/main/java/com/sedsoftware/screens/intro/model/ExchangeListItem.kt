package com.sedsoftware.screens.intro.model

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.misc.DiffItem
import com.sedsoftware.core.presentation.params.DownloadState

data class ExchangeListItem(val exchange: Exchange, val state: DownloadState) : DiffItem {

    override fun getItemName(): String = exchange.name

    override fun getItemHash(): Int = hashCode()
}
