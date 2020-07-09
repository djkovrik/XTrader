package com.sedsoftware.screens.tickers.controller.mappers

import com.sedsoftware.screens.tickers.store.TickersListStore
import com.sedsoftware.screens.tickers.store.PairSelectionStore

object CommonMappers {

    val selectorLabelToTickersIntent: PairSelectionStore.Label.() -> TickersListStore.Intent? = {
        when (this) {
            is PairSelectionStore.Label.PairSelectorAvailable -> TickersListStore.Intent.EnablePairSelector
            is PairSelectionStore.Label.CurrencyPairSelected -> TickersListStore.Intent.AddCurrencyPair(pair)
            else -> null
        }
    }
}
