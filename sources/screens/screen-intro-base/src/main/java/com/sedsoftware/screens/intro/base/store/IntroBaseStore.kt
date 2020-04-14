package com.sedsoftware.screens.intro.base.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Intent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.State

internal interface IntroBaseStore : Store<Intent, State, Nothing> {

    sealed class Intent {
        object LoadCurrencyMap : Intent()
        object EnableNavigation : Intent()
    }

    data class State(
        val state: LoadingState = LoadingState.IDLE
    )

    sealed class Result {
        object Success : Result()
        object Error : Result()
    }

    enum class LoadingState { IDLE, LOADING, ERROR, DONE }
}
