package com.sedsoftware.screens.tickers.controller.mappers

import com.sedsoftware.screens.tickers.store.TickersListStore
import com.sedsoftware.screens.tickers.ui.model.TickerListItem
import com.sedsoftware.screens.tickers.view.TickersListView

object TickersListMappers {

    val tickersStateToViewModel: TickersListStore.State.() -> TickersListView.ViewModel = {
        TickersListView.ViewModel(
            tickers = ticks.map { TickerListItem(ticker = it) },
            isFabEnabled = pairSelectorAvailable
        )
    }
}
