package com.sedsoftware.screens.tickers.controller.mappers

import com.sedsoftware.screens.tickers.store.TickersListStore
import com.sedsoftware.screens.tickers.view.TickersListView

object TickersListMappers {

    val tickersStateToViewModel: TickersListStore.State.() -> TickersListView.ViewModel = {
        TickersListView.ViewModel(
            isFabEnabled = pairSelectorAvailable
        )
    }
}
