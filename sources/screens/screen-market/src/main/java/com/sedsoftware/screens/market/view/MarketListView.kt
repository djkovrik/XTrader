package com.sedsoftware.screens.market.view

interface MarketListView {

    data class ViewModel(
        val isFabEnabled: Boolean
    )

    sealed class ViewEvent {
        // TODO
    }
}
