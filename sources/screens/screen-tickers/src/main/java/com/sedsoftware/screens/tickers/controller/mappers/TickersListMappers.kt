package com.sedsoftware.screens.tickers.controller.mappers

import com.sedsoftware.core.domain.entity.CurrencyPairTick
import com.sedsoftware.screens.tickers.store.TickersListStore
import com.sedsoftware.screens.tickers.ui.model.CurrentTrend
import com.sedsoftware.screens.tickers.ui.model.TickerListItem
import com.sedsoftware.screens.tickers.view.TickersListView

object TickersListMappers {

    val tickersStateToViewModel: TickersListStore.State.() -> TickersListView.ViewModel = {
        TickersListView.ViewModel(
            tickers = ticks.map(::mapTickModel),
            isFabEnabled = pairSelectorAvailable
        )
    }

    private fun mapTickModel(from: CurrencyPairTick): TickerListItem =
        TickerListItem(
            ticker = from,
            trend = when {
                from.price == 0f -> CurrentTrend.NONE
                from.valueChange == 0f -> CurrentTrend.STILL
                from.percentChange > 0 -> CurrentTrend.UP
                from.percentChange < 0 -> CurrentTrend.DOWN
                else -> CurrentTrend.NONE
            },
            updated = "" // TODO
        )
}
