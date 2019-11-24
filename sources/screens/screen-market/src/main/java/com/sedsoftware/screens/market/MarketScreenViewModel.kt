package com.sedsoftware.screens.market

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.utils.extension.either
import com.sedsoftware.screens.market.model.CurrencyListItem
import javax.inject.Inject

class MarketScreenViewModel @Inject constructor(
    private val managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairManager>
) : BaseViewModel() {

    internal val exchangeListLiveData = MutableLiveData<List<Exchange>>(emptyList())
    internal val baseCurrenciesLiveData = MutableLiveData<List<CurrencyListItem>>()
    internal val marketCurrenciesLiveData = MutableLiveData<List<CurrencyListItem>>()

    internal val chosenExchangeLiveData = MutableLiveData<Exchange>()
    internal val chosenBaseCurrencyLiveData = MutableLiveData<Currency>()
    internal val chosenMarketCurrencyLiveData = MutableLiveData<Currency>()

    val exchanges: List<Exchange>
        get() = exchangeListLiveData.value ?: emptyList()

    val chosenExchange: Exchange?
        get() = chosenExchangeLiveData.value

    init {
        val availableExchanges = mutableListOf<Exchange>()

        launch {
            managers.keys.forEachIndexed { index, exchange ->
                managers[exchange]
                    ?.checkIfDataAvailable()
                    ?.either(::handleFailure) { available ->
                        if (available) {
                            availableExchanges.add(exchange)
                        }
                        // Run when all exchanges checked
                        if (index == ExchangeType.values().size - 1) {
                            refreshExchangeList(availableExchanges)
                        }
                    }
            }
        }
    }

    fun getMarketForChosenBase(currency: Currency) {
        chosenBaseCurrencyLiveData.value = currency

        launch {
            chosenExchangeLiveData.value?.let { exchange ->
                managers[exchange]
                    ?.getMarketCurrencies(currency)
                    ?.either(::handleFailure, ::handleMarketLoadingCompletion)
            }
        }
    }

    fun marketCurrencyChosen(currency: Currency) {
        chosenMarketCurrencyLiveData.value = currency
    }

    private fun refreshExchangeList(exchanges: MutableList<Exchange>) {
        exchangeListLiveData.value = exchanges.sortedBy { it.name }
        if (exchanges.isNotEmpty()) {
            showChosenExchange(exchanges.first())
        }
    }

    fun showChosenExchange(exchange: Exchange) {
        chosenExchangeLiveData.value = exchange
        launch {
            managers[exchange]
                ?.getBaseCurrencies()
                ?.either(::handleFailure, ::handleBaseLoadingCompletion)
        }
    }

    private fun handleBaseLoadingCompletion(list: List<Currency>) {
        baseCurrenciesLiveData.value = list.map { CurrencyListItem(it, isBase = true, isSelected = false) }

        list.firstOrNull()?.let { first ->
            chosenBaseCurrencyLiveData.value = first
            getMarketForChosenBase(first)
        }
    }

    private fun handleMarketLoadingCompletion(list: List<Currency>) {
        marketCurrenciesLiveData.value = list.map { CurrencyListItem(it, isBase = false, isSelected = false) }
        chosenMarketCurrencyLiveData.value = list.firstOrNull()
    }
}
