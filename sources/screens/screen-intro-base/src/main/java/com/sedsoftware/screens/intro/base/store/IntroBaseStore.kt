package com.sedsoftware.screens.intro.base.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Intent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Label
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.State

interface IntroBaseStore : Store<Intent, State, Label> {

    sealed class Intent {
        object LoadCurrencyMap : Intent()
        object NavigateToNextScreen : Intent()
    }

    data class State(
        val loadingState: LoadingState = LoadingState.IDLE
    )

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
    }

    sealed class Result {
        object LoadingStarted : Result()
        object LoadingCompleted : Result()
        object LoadingFailed : Result()
    }

    enum class LoadingState { IDLE, LOADING, ERROR, DONE }
}
