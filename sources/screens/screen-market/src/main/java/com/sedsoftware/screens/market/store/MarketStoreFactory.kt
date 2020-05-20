package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.exception.MarketPairsLoadingError
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
            bootstrapper = SimpleBootstrapper(Action.FetchInitialData),
            reducer = MarketReducer
        ) {}

    private object MarketReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ExchangeListCreated -> copy(exchanges = result.exchanges)
                is Result.ExchangeSelected -> copy(selectedExchange = result.exchange)
                is Result.BaseCurrenciesListCreated -> copy(baseCurrencies = result.currencies)
                is Result.BaseCurrencySelected -> copy(selectedBaseCurrency = result.currency)
                is Result.MarketCurrenciesListCreated -> copy(marketCurrencies = result.currencies)
                is Result.MarketCurrencySelected -> copy(selectedMarketCurrency = result.currency)
                is Result.PairSelectionRequested -> copy(isPairSelectionActive = result.show)
            }
    }

    private inner class MarketExecutor : SuspendExecutor<Intent, Action, State, Result, Label>() {
        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.FetchInitialData -> {
                    try {
                        val exchanges = mutableListOf<Exchange>()

                        managers.keys.forEach { exchange ->
                            val isAvailable = managers[exchange]?.checkIfDataAvailable().orFalse()
                            if (isAvailable) {
                                exchanges.add(exchange)
                            }
                        }

                        if (exchanges.isEmpty()) return

                        dispatch(Result.ExchangeListCreated(exchanges))

                        val defaultExchange = exchanges.first()
                        dispatch(Result.ExchangeSelected(defaultExchange))

                        managers[defaultExchange]?.let { manager ->
                            val baseCurrencies = manager.getBaseCurrencies()
                            if (baseCurrencies.isEmpty()) return@let
                            dispatch(Result.BaseCurrenciesListCreated(baseCurrencies))

                            val defaultBaseCurrency = baseCurrencies.first()
                            dispatch(Result.BaseCurrencySelected(defaultBaseCurrency))

                            val marketCurrencies = manager.getMarketCurrencies(defaultBaseCurrency)
                            if (marketCurrencies.isEmpty()) return

                            dispatch(Result.MarketCurrenciesListCreated(marketCurrencies))
                            dispatch(Result.MarketCurrencySelected(marketCurrencies.first()))
                        }
                    } catch (exception: MarketPairsLoadingError) {
                        publish(Label.ErrorCaught(exception))
                    }
                }
            }
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.SelectExchange -> dispatch(Result.ExchangeSelected(intent.exchange))
                is Intent.SelectBaseCurrency -> handleBaseCurrencySelection(getState(), intent.currency)
                is Intent.SelectMarketCurrency -> dispatch(Result.MarketCurrencySelected(intent.currency))
                is Intent.ChangePairSelectionState -> dispatch(Result.PairSelectionRequested(intent.active))
            }
        }

        private suspend fun handleBaseCurrencySelection(state: State, currency: Currency) {
            dispatch(Result.BaseCurrencySelected(currency))

            val currentExchange = state.selectedExchange

            managers[currentExchange]?.let { manager ->
                val marketCurrencies = manager.getMarketCurrencies(currency)
                if (marketCurrencies.isEmpty()) return

                dispatch(Result.MarketCurrenciesListCreated(marketCurrencies))
                dispatch(Result.MarketCurrencySelected(marketCurrencies.first()))
            }
        }

        private suspend fun saveSelectedPair(state: State) {
            TODO()
        }
    }
}
