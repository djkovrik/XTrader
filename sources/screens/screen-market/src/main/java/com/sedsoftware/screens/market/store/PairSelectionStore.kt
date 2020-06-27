package com.sedsoftware.screens.market.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.utils.emptyCurrency
import com.sedsoftware.core.domain.utils.emptyExchange
import com.sedsoftware.screens.market.store.PairSelectionStore.Intent
import com.sedsoftware.screens.market.store.PairSelectionStore.Label
import com.sedsoftware.screens.market.store.PairSelectionStore.State

interface PairSelectionStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class SelectExchange(val exchange: Exchange) : Intent()
        data class SelectBaseCurrency(val currency: Currency) : Intent()
        data class SelectMarketCurrency(val currency: Currency) : Intent()
        data class ChangeExchangesDialogState(val active: Boolean) : Intent()
        data class ChangePairSelectionState(val active: Boolean) : Intent()
        object SaveCurrentPair : Intent()
    }

    data class State(
        val exchanges: List<Exchange> = emptyList(),
        val selectedExchange: Exchange = emptyExchange(),
        val baseCurrencies: List<Currency> = emptyList(),
        val selectedBaseCurrency: Currency = emptyCurrency(),
        val marketCurrencies: List<Currency> = emptyList(),
        val selectedMarketCurrency: Currency = emptyCurrency(),
        val isExchangeSelectionActive: Boolean = false,
        val isPairSelectionActive: Boolean = false
    )

    sealed class Action {
        object FetchInitialData : Action()
    }

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
        data class CurrencyPairSelected(val pair: CurrencyPair) : Label()
        object PairSelectorAvailable : Label()
    }

    sealed class Result {
        data class ExchangeListCreated(val exchanges: List<Exchange>) : Result()
        data class ExchangeSelected(val exchange: Exchange) : Result()
        data class BaseCurrenciesListCreated(val currencies: List<Currency>) : Result()
        data class BaseCurrencySelected(val currency: Currency) : Result()
        data class MarketCurrenciesListCreated(val currencies: List<Currency>) : Result()
        data class MarketCurrencySelected(val currency: Currency) : Result()
        data class ExchangeSelectionRequested(val show: Boolean) : Result()
        data class PairSelectionRequested(val show: Boolean) : Result()
    }
}
