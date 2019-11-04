package com.sedsoftware.screens.market.viewmodel

import androidx.lifecycle.MutableLiveData
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

    private val exchangeList = MutableLiveData<List<Exchange>>()
    internal val baseCurrencies = MutableLiveData<List<CurrencyListItem>>()
    internal val marketCurrencies = MutableLiveData<List<CurrencyListItem>>()

    internal val chosenExchange = MutableLiveData<Exchange>()
    internal val chosenBaseCurrency = MutableLiveData<Currency>()
    internal val chosenMarketCurrency = MutableLiveData<Currency>()

    init {
        exchangeList.value = managers.keys.toList()
        chosenExchange.value = managers.keys.toList().first()

        launch {
            chosenExchange.value?.let { exchange ->
                managers[exchange]
                    ?.getBaseCurrencies()
                    ?.either(::handleFailure, ::handleBaseLoadingCompletion)
            }
        }
    }

    fun getMarketForChosenBase(currency: Currency) {
        chosenBaseCurrency.value = currency

        launch {
            chosenExchange.value?.let { exchange ->
                managers[exchange]
                    ?.getMarketCurrencies(currency)
                    ?.either(::handleFailure, ::handleMarketLoadingCompletion)
            }
        }
    }

    fun marketCurrencyChosen(currency: Currency) {
        chosenMarketCurrency.value = currency
    }

    private fun handleBaseLoadingCompletion(list: List<Currency>) {
        baseCurrencies.value = list.map { CurrencyListItem(it, isBase = true, isSelected = false) }

        list.firstOrNull()?.let { first ->
            chosenBaseCurrency.value = first
            getMarketForChosenBase(first)
        }
    }

    private fun handleMarketLoadingCompletion(list: List<Currency>) {
        marketCurrencies.value = list.map { CurrencyListItem(it, isBase = false, isSelected = false) }
        chosenMarketCurrency.value = list.firstOrNull()
    }
}
