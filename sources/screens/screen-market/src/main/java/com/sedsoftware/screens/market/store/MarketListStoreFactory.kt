package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.screens.market.store.MarketListStore.Intent
import com.sedsoftware.screens.market.store.MarketListStore.Label
import com.sedsoftware.screens.market.store.MarketListStore.Result
import com.sedsoftware.screens.market.store.MarketListStore.State
import com.sedsoftware.screens.market.store.PairSelectionStore.Action

class MarketListStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): MarketListStore =
        object : MarketListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "MarketListStore",
            initialState = State(),
            executorFactory = ::MarketListExecutor,
            reducer = MarketReducer
        ) {}

    private object MarketReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                Result.SelectorAvailable -> copy(pairSelectorAvailable = true)
            }
    }

    private inner class MarketListExecutor : SuspendExecutor<Intent, Action, State, Result, Label>() {
        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.EnablePairSelector -> dispatch(Result.SelectorAvailable)
            }
        }
    }
}
