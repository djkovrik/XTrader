package com.sedsoftware.screens.intro.exchanges.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
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
import javax.inject.Inject

class IntroExchangesStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val flowSwitcher: FlowSwitcher,
    private val loaders: Map<Exchange, @JvmSuppressWildcards CurrencyPairsLoader>
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
                is Result.Created -> copy(
                    exchanges = result.list
                )
                is Result.InProgress -> copy(
                    exchanges = exchanges.update(
                        exchange = result.exchange,
                        state = DownloadState.IN_PROGRESS
                    )
                )
                is Result.Completed -> copy(
                    exchanges = exchanges.update(
                        exchange = result.exchange,
                        state = DownloadState.COMPLETED
                    )
                )
                is Result.Error -> copy(
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
            super.executeAction(action, getState)
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
        }
    }
}
