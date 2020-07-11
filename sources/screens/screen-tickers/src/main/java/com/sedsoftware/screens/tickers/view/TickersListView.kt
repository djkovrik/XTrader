package com.sedsoftware.screens.tickers.view

import com.sedsoftware.screens.tickers.ui.model.TickerListItem

interface TickersListView {

    data class ViewModel(
        val tickers: List<TickerListItem>,
        val isFabEnabled: Boolean
    )

    sealed class ViewEvent {
        // TODO
    }
}
