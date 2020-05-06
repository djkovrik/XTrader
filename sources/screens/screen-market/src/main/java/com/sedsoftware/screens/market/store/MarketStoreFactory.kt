package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.screens.market.store.MarketStore.Action
import com.sedsoftware.screens.market.store.MarketStore.Intent
import com.sedsoftware.screens.market.store.MarketStore.Label
import com.sedsoftware.screens.market.store.MarketStore.Result
import com.sedsoftware.screens.market.store.MarketStore.State

class MarketStoreFactory(
    private val storeFactory: StoreFactory,
    private val managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairsManager>
) {

    fun create(): MarketStore =
        object : MarketStore, Store<Intent, State, Label> by storeFactory.create(
            name = "MarketStore",
            initialState = State(),
            executorFactory = ::MarketExecutor,
            bootstrapper = MarketBootstrapper(),
            reducer = MarketReducer
        ) {}

    private object MarketReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ExchangesListReady -> copy(exchanges = result.exchanges)
                is Result.BaseCurrenciesListReady -> copy(baseCurrencies = result.currencies)
                is Result.MarketCurrenciesListReady -> copy(marketCurrencies = result.currencies)
                is Result.BaseCurrencySelected -> copy(baseCurrencies = result.currencies)
                is Result.MarketCurrencySelected -> copy(marketCurrencies = result.currencies)
                is Result.PairSelectionDisplayed -> copy(isPairSelectionActive = true)
                is Result.PairSelectionClosed -> copy(isPairSelectionActive = false)
            }
    }

    private inner class MarketBootstrapper : SuspendBootstrapper<Action>() {
        override suspend fun bootstrap() {

        }
    }

    private inner class MarketExecutor : SuspendExecutor<Intent, Action, State, Result, Label>() {
        override suspend fun executeAction(action: Action, getState: () -> State) {
            super.executeAction(action, getState)
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
        }
    }
}
