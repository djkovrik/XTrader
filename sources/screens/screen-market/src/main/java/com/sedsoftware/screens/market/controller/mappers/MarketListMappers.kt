package com.sedsoftware.screens.market.controller.mappers

import com.sedsoftware.screens.market.store.MarketListStore
import com.sedsoftware.screens.market.view.MarketListView

object MarketListMappers {

    val stateToViewModel: MarketListStore.State.() -> MarketListView.ViewModel = {
        MarketListView.ViewModel(
            isFabEnabled = pairSelectorAvailable
        )
    }
}
