package com.sedsoftware.screens.intro.base.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Intent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.LoadingState
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Result
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.State
import javax.inject.Inject

class IntroBaseStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val currencyMapLoader: CurrencyMapLoader
) {

    fun create(): IntroBaseStore =
        object : IntroBaseStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "IntroBaseStore",
            initialState = State(),
            executorFactory = ::IntroBaseExecutor,
            reducer = IntroBaseReducer
        ) {}

    private object IntroBaseReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.InProgress -> copy(loadingState = LoadingState.LOADING)
                is Result.Success -> copy(loadingState = LoadingState.DONE)
                is Result.Error -> copy(loadingState = LoadingState.ERROR)
            }

    }

    private inner class IntroBaseExecutor : SuspendExecutor<Intent, Nothing, State, Result, Nothing>() {

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {

            when (intent) {
                is Intent.LoadCurrencyMap -> downloadCurrencyMap()
            }
        }

        private suspend fun downloadCurrencyMap() {
            dispatch(Result.InProgress)

            try {
                currencyMapLoader.loadCurrencyMap()
                dispatch(Result.Success)
            } catch (throwable: Throwable) {
                dispatch(Result.Error(throwable))
            }
        }
    }
}
