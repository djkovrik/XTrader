package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.presentation.extension.orFalse
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
            val exchanges = mutableListOf<Exchange>()

            managers.keys.forEach { exchange ->
                val isAvailable = managers[exchange]?.checkIfDataAvailable().orFalse()
                if (isAvailable) {
                    exchanges.add(exchange)
                }
            }

            if (exchanges.isEmpty()) return

            dispatch(Action.CreateExchangesList(exchanges))

            val defaultExchange = exchanges.first()
            dispatch(Action.HighlightExchange(defaultExchange))

            managers[defaultExchange]?.let { manager ->
                val baseCurrencies = manager.getBaseCurrencies()
                if (baseCurrencies.isEmpty()) return@let

                val defaultBaseCurrency = baseCurrencies.first()
                dispatch(Action.HighlightBaseCurrency(defaultBaseCurrency))

                val marketCurrencies = manager.getMarketCurrencies(defaultBaseCurrency)
                if (marketCurrencies.isEmpty()) return

                dispatch(Action.HighlightMarketCurrency(marketCurrencies.first()))
            }
        }
    }

    private inner class MarketExecutor : SuspendExecutor<Intent, Action, State, Result, Label>() {
        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.CreateExchangesList -> {
                }
                is Action.HighlightExchange -> {
                }
                is Action.FetchBaseCurrencies -> {
                }
                is Action.HighlightBaseCurrency -> {
                }
                is Action.FetchMarketCurrencies -> {
                }
                is Action.HighlightMarketCurrency -> {
                }
            }
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.SetBaseCurrencies -> {
                }
                is Intent.SelectBaseCurrency -> {
                }
                is Intent.SetMarketCurrencies -> {
                }
                is Intent.SelectMarketCurrency -> {
                }
                is Intent.SelectExchange -> {
                }
                is Intent.SaveSelectedPair -> {
                }
                is Intent.ShowPairSelection -> {
                }
                is Intent.HidePairSelection -> {
                }
            }
        }
    }
}
