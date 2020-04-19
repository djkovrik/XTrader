package com.sedsoftware.screens.intro.base.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Intent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.State

interface IntroBaseStore : Store<Intent, State, Nothing> {

    sealed class Intent {
        object LoadCurrencyMap : Intent()
    }

    data class State(
        val loadingState: LoadingState = LoadingState.IDLE
    )

    sealed class Result {
        object InProgress : Result()
        object Success : Result()
        data class Error(val throwable: Throwable) : Result()
    }

    enum class LoadingState { IDLE, LOADING, ERROR, DONE }
}
