package com.sedsoftware.screens.intro.exchanges.store.model

import com.sedsoftware.core.domain.entity.Exchange

fun ExchangeListItem.update(state: DownloadState): ExchangeListItem =
    copy(state = state)

fun Iterable<ExchangeListItem>.update(
    exchange: Exchange,
    state: DownloadState
): List<ExchangeListItem> =
    map { if (it.exchange == exchange) it.update(state) else it }
