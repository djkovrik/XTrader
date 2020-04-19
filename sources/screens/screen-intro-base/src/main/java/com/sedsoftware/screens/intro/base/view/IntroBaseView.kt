package com.sedsoftware.screens.intro.base.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.sedsoftware.screens.intro.base.view.IntroBaseView.ViewEvent
import com.sedsoftware.screens.intro.base.view.IntroBaseView.ViewModel

interface IntroBaseView : MviView<ViewModel, ViewEvent> {

    data class ViewModel(
        val isDownloadButtonAvailable: Boolean,
        val isNextButtonAvailable: Boolean,
        val isProgressVisible: Boolean
    )

    sealed class ViewEvent {
        object DownloadClicked : ViewEvent()
    }
}
