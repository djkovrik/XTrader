package com.sedsoftware.screens.tickers.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import com.sedsoftware.screens.tickers.store.TickersListStore.Intent
import com.sedsoftware.screens.tickers.store.TickersListStore.Label
import com.sedsoftware.screens.tickers.store.TickersListStore.State

interface TickersListStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class AddCurrencyPair(val pair: CurrencyPair) : Intent()
        object EnablePairSelector : Intent()
    }

    data class State(
        val ticks: List<CurrencyPairTick> = emptyList(),
        val pairSelectorAvailable: Boolean = false
    )

    sealed class Action {
        // TODO
    }

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
        object WatchListRefreshed : Label()
    }

    sealed class Result {
        data class TicksRefreshed(val list: List<CurrencyPairTick>) : Result()
        object SelectorAvailable : Result()
    }
}
