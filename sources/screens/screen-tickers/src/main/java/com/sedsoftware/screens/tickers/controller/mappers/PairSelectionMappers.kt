package com.sedsoftware.screens.tickers.controller.mappers

import com.sedsoftware.screens.tickers.TickersEvent
import com.sedsoftware.screens.tickers.store.PairSelectionStore
import com.sedsoftware.screens.tickers.ui.model.CurrencyListItem
import com.sedsoftware.screens.tickers.ui.model.ExchangeListItem
import com.sedsoftware.screens.tickers.view.PairSelectionView

internal object PairSelectionMappers {

    val selectorViewEventToIntent: PairSelectionView.ViewEvent.() -> PairSelectionStore.Intent = {
        when (this) {
            is PairSelectionView.ViewEvent.ExchangesDialogRequested -> PairSelectionStore.Intent.ChangeExchangesDialogState(true)
            is PairSelectionView.ViewEvent.ExchangesDialogClosed -> PairSelectionStore.Intent.ChangeExchangesDialogState(false)
            is PairSelectionView.ViewEvent.AddNewTicker -> PairSelectionStore.Intent.SaveCurrentPair
            is PairSelectionView.ViewEvent.ExchangeSelected -> PairSelectionStore.Intent.SelectExchange(exchange)
            is PairSelectionView.ViewEvent.BaseCurrencySelected -> PairSelectionStore.Intent.SelectBaseCurrency(currency)
            is PairSelectionView.ViewEvent.MarketCurrencySelected -> PairSelectionStore.Intent.SelectMarketCurrency(currency)
            is PairSelectionView.ViewEvent.PairSelectionStateChanged -> PairSelectionStore.Intent.ChangePairSelectionState(show)
        }
    }

    val selectorStateToViewModel: PairSelectionStore.State.() -> PairSelectionView.ViewModel = {
        val exchangeList = exchanges.map { exchangeItem ->
            ExchangeListItem(
                exchange = exchangeItem,
                isSelected = exchangeItem == selectedExchange
            )
        }

        val baseCurrencyList = baseCurrencies.map { currencyItem ->
            CurrencyListItem(
                currency = currencyItem,
                isBase = true,
                isSelected = currencyItem == selectedBaseCurrency
            )
        }

        val marketCurrencyList = marketCurrencies.map { currencyItem ->
            CurrencyListItem(
                currency = currencyItem,
                isBase = false,
                isSelected = currencyItem == selectedMarketCurrency
            )
        }

        PairSelectionView.ViewModel(
            exchanges = exchangeList,
            baseCurrencies = baseCurrencyList,
            marketCurrencies = marketCurrencyList,
            isExchangesDialogActive = isExchangeSelectionActive,
            isPairSelectionViewActive = isPairSelectionActive,
            isAddButtonEnabled = exchanges.isNotEmpty()
                    && baseCurrencyList.isNotEmpty()
                    && marketCurrencyList.isNotEmpty()
        )
    }

    val selectorLabelToEvent: PairSelectionStore.Label.() -> TickersEvent? = {
        when (this) {
            is PairSelectionStore.Label.ErrorCaught -> TickersEvent.HandleError(throwable)
            else -> null
        }
    }
}
