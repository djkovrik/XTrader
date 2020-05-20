package com.sedsoftware.screens.intro.exchanges.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Intent
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Label
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.State
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem

interface IntroExchangesStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class StartDownloading(val exchange: Exchange) : Intent()
        object NavigateToNextScreen : Intent()

    }

    data class State(
        val exchanges: List<ExchangeListItem> = emptyList()
    )

    sealed class Action {
        data class CreateExchangesList(val list: List<ExchangeListItem>) : Action()
    }

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
    }

    sealed class Result {
        data class ExchangeListCreated(val list: List<ExchangeListItem>) : Result()
        data class LoadingStarted(val exchange: Exchange) : Result()
        data class LoadingCompleted(val exchange: Exchange) : Result()
        data class LoadingFailed(val exchange: Exchange) : Result()
    }
}
