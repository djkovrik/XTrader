package com.sedsoftware.screens.intro.base.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Intent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Label
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.LoadingState
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Result
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.State

class IntroBaseStoreFactory(
    private val storeFactory: StoreFactory,
    private val currencyMapLoader: CurrencyMapLoader,
    private val startingFlowCoordinator: StartingFlowCoordinator
) {

    fun create(): IntroBaseStore =
        object : IntroBaseStore, Store<Intent, State, Label> by storeFactory.create(
            name = "IntroBaseStore",
            initialState = State(),
            executorFactory = ::IntroBaseExecutor,
            reducer = IntroBaseReducer
        ) {}

    private object IntroBaseReducer : Reducer<State, Result> {

        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.LoadingStarted -> copy(loadingState = LoadingState.LOADING)
                is Result.LoadingCompleted -> copy(loadingState = LoadingState.DONE)
                is Result.LoadingFailed -> copy(loadingState = LoadingState.ERROR)
            }
    }

    private inner class IntroBaseExecutor : SuspendExecutor<Intent, Nothing, State, Result, Label>() {

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.LoadCurrencyMap -> downloadCurrencyMap()
                is Intent.NavigateToNextScreen -> navigateToExchangesScreen()
            }
        }

        private suspend fun downloadCurrencyMap() {
            dispatch(Result.LoadingStarted)

            try {
                currencyMapLoader.loadCurrencyMap()
                dispatch(Result.LoadingCompleted)
            } catch (throwable: Throwable) {
                dispatch(Result.LoadingFailed)
                publish(Label.ErrorCaught(throwable))
            }
        }

        private fun navigateToExchangesScreen() {
            startingFlowCoordinator.navigateToExchangeScreen()
        }
    }
}
