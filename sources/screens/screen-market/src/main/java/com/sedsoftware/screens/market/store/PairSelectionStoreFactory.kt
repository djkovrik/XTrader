package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.exception.MarketPairsLoadingError
import com.sedsoftware.core.domain.interactor.CurrencyPairsManager
import com.sedsoftware.core.presentation.extension.orFalse
import com.sedsoftware.screens.market.store.PairSelectionStore.Action
import com.sedsoftware.screens.market.store.PairSelectionStore.Intent
import com.sedsoftware.screens.market.store.PairSelectionStore.Label
import com.sedsoftware.screens.market.store.PairSelectionStore.Result
import com.sedsoftware.screens.market.store.PairSelectionStore.State

class PairSelectionStoreFactory(
    private val storeFactory: StoreFactory,
    private val managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairsManager>
) {

    fun create(): PairSelectionStore =
        object : PairSelectionStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PairSelectionStore",
            initialState = State(),
            executorFactory = ::PairSelectionExecutor,
            bootstrapper = SimpleBootstrapper(Action.FetchInitialData),
            reducer = PairSelectionReducer
        ) {}

    private object PairSelectionReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ExchangeListCreated -> copy(exchanges = result.exchanges)
                is Result.ExchangeSelected -> copy(selectedExchange = result.exchange)
                is Result.BaseCurrenciesListCreated -> copy(baseCurrencies = result.currencies)
                is Result.BaseCurrencySelected -> copy(selectedBaseCurrency = result.currency)
                is Result.MarketCurrenciesListCreated -> copy(marketCurrencies = result.currencies)
                is Result.MarketCurrencySelected -> copy(selectedMarketCurrency = result.currency)
                is Result.ExchangeSelectionRequested -> copy(isExchangeSelectionActive = result.show)
                is Result.PairSelectionRequested -> copy(isPairSelectionActive = result.show)
            }
    }

    private inner class PairSelectionExecutor : SuspendExecutor<Intent, Action, State, Result, Label>() {
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
                        loadCurrenciesForExchange(defaultExchange)
                    } catch (exception: MarketPairsLoadingError) {
                        publish(Label.ErrorCaught(exception))
                    }
                }
            }
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.SelectExchange -> handleExchangeSelection(intent.exchange)
                is Intent.SelectBaseCurrency -> handleBaseCurrencySelection(getState(), intent.currency)
                is Intent.SelectMarketCurrency -> dispatch(Result.MarketCurrencySelected(intent.currency))
                is Intent.ChangeExchangesDialogState -> dispatch(Result.ExchangeSelectionRequested(intent.active))
                is Intent.ChangePairSelectionState -> dispatch(Result.PairSelectionRequested(intent.active))
                is Intent.SaveCurrentPair -> requestForCurrencyPairSaving(getState())
            }
        }

        private suspend fun handleExchangeSelection(exchange: Exchange) {
            dispatch(Result.ExchangeSelected(exchange))
            loadCurrenciesForExchange(exchange)
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

        private suspend fun loadCurrenciesForExchange(exchange: Exchange) {
            managers[exchange]?.let { manager ->
                val baseCurrencies = manager.getBaseCurrencies()
                if (baseCurrencies.isEmpty()) return@let
                dispatch(Result.BaseCurrenciesListCreated(baseCurrencies))

                val defaultBaseCurrency = baseCurrencies.first()
                dispatch(Result.BaseCurrencySelected(defaultBaseCurrency))

                val marketCurrencies = manager.getMarketCurrencies(defaultBaseCurrency)
                if (marketCurrencies.isEmpty()) return

                dispatch(Result.MarketCurrenciesListCreated(marketCurrencies))
                dispatch(Result.MarketCurrencySelected(marketCurrencies.first()))
                publish(Label.PairSelectorAvailable)
            }
        }

        private fun requestForCurrencyPairSaving(state: State) {
            val pair = object : CurrencyPair {
                override val exchange: Exchange = state.selectedExchange
                override val baseCurrency: Currency = state.selectedBaseCurrency
                override val marketCurrency: Currency = state.selectedMarketCurrency
                override val symbol: String =
                    "${state.selectedBaseCurrency.name}${state.selectedMarketCurrency.name}".toUpperCase()
            }

            publish(Label.CurrencyPairSelected(pair))
        }
    }
}
