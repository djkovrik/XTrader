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
            bootstrapper = IntroExchangesBootstrapper,
            reducer = IntroExchangesReducer
        ) {}

    private object IntroExchangesReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State {
            TODO()
        }
    }

    private object IntroExchangesBootstrapper : SuspendBootstrapper<Action>() {
        override suspend fun bootstrap() {
            dispatch(Action.CreateExchangesList)
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
