package com.sedsoftware.screens.intro.exchanges.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewEvent
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewModel

interface IntroExchangesView : MviView<ViewModel, ViewEvent> {

    data class ViewModel(
        val listItems: List<ExchangeListItem>,
        val isDoneButtonAvailable: Boolean
    )

    sealed class ViewEvent {
        data class DownloadClicked(val exchange: Exchange) : ViewEvent()
        data class RetryClicked(val exchange: Exchange) : ViewEvent()
        object DoneClicked : ViewEvent()
    }
}
