package com.sedsoftware.screens.intro.exchanges.store

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem

interface IntroExchangesStore {

    sealed class Intent {
        data class StartDownloading(val exchange: Exchange) : Intent()
        data class MarkAsCompleted(val exchange: Exchange) : Intent()
        data class MarkAsError(val exchange: Exchange) : Intent()
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
        data class Created(val list: List<ExchangeListItem>) : Result()
        data class InProgress(val exchange: Exchange) : Result()
        data class Completed(val exchange: Exchange) : Result()
        data class Error(val exchange: Exchange) : Result()
    }
}
