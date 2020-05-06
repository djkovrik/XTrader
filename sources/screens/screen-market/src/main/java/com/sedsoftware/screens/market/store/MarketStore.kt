package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.utils.emptyCurrency
import com.sedsoftware.core.domain.utils.emptyExchange
import com.sedsoftware.screens.market.store.MarketStore.Intent
import com.sedsoftware.screens.market.store.MarketStore.Label
import com.sedsoftware.screens.market.store.MarketStore.State

interface MarketStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class SetBaseCurrencies(val currencies: List<Currency>)
        data class SelectBaseCurrency(val currency: Currency) : Intent()
        data class SetMarketCurrencies(val currencies: List<Currency>)
        data class SelectMarketCurrency(val currency: Currency) : Intent()
        data class SelectExchange(val exchange: Exchange) : Intent()
        data class SaveSelectedPair(val base: Currency, val market: Currency, val exchange: Exchange)
        object ShowPairSelection : Intent()
        object HidePairSelection : Intent()
    }

    data class State(
        val baseCurrencies: List<Currency> = emptyList(),
        val selectedBaseCurrency: Currency = emptyCurrency(),
        val marketCurrencies: List<Currency> = emptyList(),
        val selectedMarketCurrency: Currency = emptyCurrency(),
        val exchanges: List<Exchange> = emptyList(),
        val selectedExchange: Exchange = emptyExchange(),
        val isPairSelectionActive: Boolean = false
    )

    sealed class Action {
        object CreateExchangesList: Action()
        object FetchBaseCurrencies : Action()
        data class FetchMarketCurrencies(val baseCurrency: Currency) : Action()
    }

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
    }

    sealed class Result {
        data class ExchangesListReady(val exchanges: List<Exchange>) : Result()
        data class BaseCurrenciesListReady(val currencies: List<Currency>) : Result()
        data class MarketCurrenciesListReady(val currencies: List<Currency>) : Result()
        data class BaseCurrencySelected(val currencies: List<Currency>) : Result()
        data class MarketCurrencySelected(val currencies: List<Currency>) : Result()
        object PairSelectionDisplayed : Result()
        object PairSelectionClosed : Result()
    }
}