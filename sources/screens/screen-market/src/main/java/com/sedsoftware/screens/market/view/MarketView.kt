package com.sedsoftware.screens.market.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.screens.market.view.MarketView.ViewEvent
import com.sedsoftware.screens.market.view.MarketView.ViewModel
import com.sedsoftware.screens.market.view.model.CurrencyListItem
import com.sedsoftware.screens.market.view.model.ExchangeListItem

interface MarketView : MviView<ViewModel, ViewEvent> {

    data class ViewModel(
        val exchanges: List<ExchangeListItem>,
        val baseCurrencies: List<CurrencyListItem>,
        val marketCurrencies: List<CurrencyListItem>,
        val isPairSelectionViewActive: Boolean
    )

    sealed class ViewEvent {
        data class ExchangeSelected(val exchange: Exchange) : ViewEvent()
        data class BaseCurrencySelected(val currency: Currency) : ViewEvent()
        object OpenPairPicker : ViewEvent()
        object ClosePairPicker : ViewEvent()
        object SaveButtonClicked : ViewEvent()
    }
}
