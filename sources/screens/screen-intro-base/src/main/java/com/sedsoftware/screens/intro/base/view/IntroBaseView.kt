package com.sedsoftware.screens.intro.base.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.sedsoftware.screens.intro.base.view.IntroBaseView.Event
import com.sedsoftware.screens.intro.base.view.IntroBaseView.Model

interface IntroBaseView : MviView<Model, Event> {

    data class Model(
        val isInProgress: Boolean,
        val isDone: Boolean
    )

    sealed class Event {
        object LoadButtonClicked : Event()
        object NextButtonClicked : Event()
    }
}
