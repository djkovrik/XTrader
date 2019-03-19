package com.sedsoftware.screens.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.utils.enums.DownloadState
import javax.inject.Inject

class IntroScreenViewModel @Inject constructor(
    private val loaders: Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>
) : BaseViewModel() {

    var exchanges: MutableLiveData<Map<Exchange, DownloadState>> = MutableLiveData()

    fun showExchanges() {

        val initialList = mutableMapOf<Exchange, DownloadState>()

        loaders.keys.forEach { exchange ->
            initialList[exchange] = DownloadState.AVAILABLE
        }

        exchanges.value = initialList
    }
}
