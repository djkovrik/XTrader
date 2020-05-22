package com.sedsoftware.screens.market.controller

import com.sedsoftware.screens.market.MarketEvent
import com.sedsoftware.screens.market.store.MarketStore
import com.sedsoftware.screens.market.view.MarketView
import com.sedsoftware.screens.market.view.model.CurrencyListItem
import com.sedsoftware.screens.market.view.model.ExchangeListItem

internal fun MarketView.ViewEvent.toIntent(): MarketStore.Intent =
    when (this) {
        is MarketView.ViewEvent.ExchangesDialogRequested -> MarketStore.Intent.ChangeExchangesDialogState(true)
        is MarketView.ViewEvent.ExchangesDialogClosed -> MarketStore.Intent.ChangeExchangesDialogState(false)
        is MarketView.ViewEvent.ExchangeSelected -> MarketStore.Intent.SelectExchange(exchange)
        is MarketView.ViewEvent.BaseCurrencySelected -> MarketStore.Intent.SelectBaseCurrency(currency)
        is MarketView.ViewEvent.MarketCurrencySelected -> MarketStore.Intent.SelectMarketCurrency(currency)
        is MarketView.ViewEvent.PairSelectionStateChanged -> MarketStore.Intent.ChangePairSelectionState(show)
    }

internal fun MarketStore.State.toViewModel(): MarketView.ViewModel {
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

    return MarketView.ViewModel(
        exchanges = exchangeList,
        baseCurrencies = baseCurrencyList,
        marketCurrencies = marketCurrencyList,
        isFabAvailable = baseCurrencyList.isNotEmpty() && marketCurrencyList.isNotEmpty(),
        isExchangesDialogActive = isExchangeSelectionActive,
        isPairSelectionViewActive = isPairSelectionActive
    )
}

internal fun MarketStore.Label.toEvent(): MarketEvent =
    when (this) {
        is MarketStore.Label.ErrorCaught -> MarketEvent.HandleError(throwable)
    }
