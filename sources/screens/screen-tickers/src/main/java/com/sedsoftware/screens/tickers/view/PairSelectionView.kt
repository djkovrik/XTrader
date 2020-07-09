package com.sedsoftware.screens.tickers.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.screens.tickers.ui.model.CurrencyListItem
import com.sedsoftware.screens.tickers.ui.model.ExchangeListItem
import com.sedsoftware.screens.tickers.view.PairSelectionView.ViewEvent
import com.sedsoftware.screens.tickers.view.PairSelectionView.ViewModel

interface PairSelectionView : MviView<ViewModel, ViewEvent> {

    data class ViewModel(
        val exchanges: List<ExchangeListItem>,
        val baseCurrencies: List<CurrencyListItem>,
        val marketCurrencies: List<CurrencyListItem>,
        val isExchangesDialogActive: Boolean,
        val isPairSelectionViewActive: Boolean,
        val isAddButtonEnabled: Boolean
    )

    sealed class ViewEvent {
        object ExchangesDialogRequested : ViewEvent()
        object ExchangesDialogClosed : ViewEvent()
        object AddNewTicker : ViewEvent()
        data class ExchangeSelected(val exchange: Exchange) : ViewEvent()
        data class BaseCurrencySelected(val currency: Currency) : ViewEvent()
        data class MarketCurrencySelected(val currency: Currency) : ViewEvent()
        data class PairSelectionStateChanged(val show: Boolean) : ViewEvent()
    }
}
