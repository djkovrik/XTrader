package com.sedsoftware.screens.intro.base.controller

import com.sedsoftware.core.presentation.event.OneTimeEvent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.LoadingState
import com.sedsoftware.screens.intro.base.view.IntroBaseView

internal fun IntroBaseView.ViewEvent.toIntent(): IntroBaseStore.Intent =
    when (this) {
        is IntroBaseView.ViewEvent.DownloadClicked -> IntroBaseStore.Intent.LoadCurrencyMap
        is IntroBaseView.ViewEvent.NextClicked -> IntroBaseStore.Intent.NavigateToNextScreen
    }

internal fun IntroBaseStore.State.toViewModel(): IntroBaseView.ViewModel =
    when (loadingState) {
        LoadingState.IDLE -> IntroBaseView.ViewModel(
            isDownloadButtonAvailable = true,
            isNextButtonAvailable = false,
            isProgressVisible = false
        )

        LoadingState.LOADING -> IntroBaseView.ViewModel(
            isDownloadButtonAvailable = false,
            isNextButtonAvailable = false,
            isProgressVisible = true
        )

        LoadingState.ERROR -> IntroBaseView.ViewModel(
            isDownloadButtonAvailable = true,
            isNextButtonAvailable = false,
            isProgressVisible = false
        )

        LoadingState.DONE -> IntroBaseView.ViewModel(
            isDownloadButtonAvailable = false,
            isNextButtonAvailable = true,
            isProgressVisible = false
        )
    }

internal fun IntroBaseStore.Label.toEvent(): OneTimeEvent =
    when (this) {
        is IntroBaseStore.Label.ErrorCaught -> OneTimeEvent.HandleError(throwable)
    }
