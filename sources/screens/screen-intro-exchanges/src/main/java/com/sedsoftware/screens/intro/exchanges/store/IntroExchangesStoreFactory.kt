package com.sedsoftware.screens.intro.exchanges.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairsLoader
import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Action
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Intent
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Label
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Result
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.State
import com.sedsoftware.screens.intro.exchanges.store.model.DownloadState
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem
import com.sedsoftware.screens.intro.exchanges.store.model.update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IntroExchangesStoreFactory(
    private val storeFactory: StoreFactory,
    private val flowSwitcher: FlowSwitcher,
    private val loaders: Map<ExchangeType, @JvmSuppressWildcards CurrencyPairsLoader>
) {

    fun create(): IntroExchangesStore =
        object : IntroExchangesStore, Store<Intent, State, Label> by storeFactory.create(
            name = "IntroExchangesStore",
            initialState = State(),
            executorFactory = ::IntroExchangesExecutor,
            bootstrapper = IntroExchangesBootstrapper(),
            reducer = IntroExchangesReducer
        ) {}

    private object IntroExchangesReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ExchangeListCreated -> copy(
                    exchanges = result.list
                )
                is Result.LoadingStarted -> copy(
                    exchanges = exchanges.update(
                        exchange = result.exchange,
                        state = DownloadState.IN_PROGRESS
                    )
                )
                is Result.LoadingCompleted -> copy(
                    exchanges = exchanges.update(
                        exchange = result.exchange,
                        state = DownloadState.COMPLETED
                    )
                )
                is Result.LoadingFailed -> copy(
                    exchanges = exchanges.update(
                        exchange = result.exchange,
                        state = DownloadState.ERROR
                    )
                )
            }
    }

    private inner class IntroExchangesBootstrapper : SuspendBootstrapper<Action>() {

        override suspend fun bootstrap() {
            val list = withContext(Dispatchers.IO) {
                loaders.keys.map {
                    ExchangeListItem(exchange = it, state = DownloadState.AVAILABLE)
                }
            }

            dispatch(Action.CreateExchangesList(list))
        }
    }

    private inner class IntroExchangesExecutor : SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.CreateExchangesList -> {
                    dispatch(Result.ExchangeListCreated(action.list))
                }
            }
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.StartDownloading -> startDownloading(intent.exchange)
                is Intent.NavigateToNextScreen -> switchToRegularFlow()
            }
        }

        private suspend fun startDownloading(exchange: Exchange) {
            loaders[exchange]?.let { loader ->
                dispatch(Result.LoadingStarted(exchange))

                try {
                    loader.fetchCurrencyPairs()
                    dispatch(Result.LoadingCompleted(exchange))
                } catch (throwable: Throwable) {
                    dispatch(Result.LoadingFailed(exchange))
                    publish(Label.ErrorCaught(throwable))
                }
            }
        }

        private fun switchToRegularFlow() {
            flowSwitcher.switchToRegularFlow()
        }
    }
}
