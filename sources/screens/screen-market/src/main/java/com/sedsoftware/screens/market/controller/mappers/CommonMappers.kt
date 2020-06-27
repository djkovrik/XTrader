package com.sedsoftware.screens.market.controller.mappers

import com.sedsoftware.screens.market.store.MarketListStore
import com.sedsoftware.screens.market.store.PairSelectionStore

object CommonMappers {

    val selectorLabelToMarketIntent: PairSelectionStore.Label.() -> MarketListStore.Intent? = {
        when (this) {
            is PairSelectionStore.Label.PairSelectorAvailable -> MarketListStore.Intent.EnablePairSelector
            is PairSelectionStore.Label.CurrencyPairSelected -> MarketListStore.Intent.AddCurrencyPair(pair)
            else -> null
        }
    }
}
