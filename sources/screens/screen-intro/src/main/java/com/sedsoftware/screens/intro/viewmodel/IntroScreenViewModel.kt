package com.sedsoftware.screens.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.screens.intro.model.ExchangeItem
import javax.inject.Inject

class IntroScreenViewModel @Inject constructor(
    private val exchangeList: Set<@JvmSuppressWildcards Exchange>
) : BaseViewModel() {

    var exchanges: MutableLiveData<List<ExchangeItem>> = MutableLiveData()

    fun showExchanges() {

        // Dummy list
        exchanges.value = listOf(
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE,
            ExchangeType.BINANCE
        ).map { ExchangeItem(it) }
    }
}
