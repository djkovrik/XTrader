package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.screens.market.store.MarketListStore.Intent
import com.sedsoftware.screens.market.store.MarketListStore.Label
import com.sedsoftware.screens.market.store.MarketListStore.State

interface MarketListStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class AddCurrencyPair(val pair: CurrencyPair) : Intent()
        object EnablePairSelector : Intent()
    }

    data class State(
        val pairSelectorAvailable: Boolean = false
    )

    sealed class Action {
        // TODO
    }

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
    }

    sealed class Result {
        object SelectorAvailable : Result()
    }
}
