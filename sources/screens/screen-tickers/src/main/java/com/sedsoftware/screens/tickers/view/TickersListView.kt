package com.sedsoftware.screens.tickers.view

interface TickersListView {

    data class ViewModel(
        val isFabEnabled: Boolean
    )

    sealed class ViewEvent {
        // TODO
    }
}
