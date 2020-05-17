package com.sedsoftware.screens.intro.exchanges.controller

import com.sedsoftware.screens.intro.exchanges.IntroExchangesEvent
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore
import com.sedsoftware.screens.intro.exchanges.store.model.DownloadState
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView

internal fun IntroExchangesView.ViewEvent.toIntent(): IntroExchangesStore.Intent =
    when (this) {
        is IntroExchangesView.ViewEvent.DownloadClicked -> IntroExchangesStore.Intent.StartDownloading(exchange)
        is IntroExchangesView.ViewEvent.DoneClicked -> IntroExchangesStore.Intent.NavigateToNextScreen
    }

internal fun IntroExchangesStore.State.toViewModel(): IntroExchangesView.ViewModel {
    val canNavigateFurther = exchanges.count { it.state == DownloadState.IN_PROGRESS } == 0 &&
            exchanges.count { it.state == DownloadState.COMPLETED } > 0

    return IntroExchangesView.ViewModel(listItems = exchanges, isDoneButtonAvailable = canNavigateFurther)
}

internal fun IntroExchangesStore.Label.toEvent(): IntroExchangesEvent =
    when (this) {
        is IntroExchangesStore.Label.ErrorCaught -> IntroExchangesEvent.HandleError(throwable)
    }
