package com.sedsoftware.screens.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.tools.api.Logger
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.core.utils.enums.DownloadState
import javax.inject.Inject

class IntroScreenViewModel @Inject constructor(
    private val loaders: Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>,
    private val logger: Logger
) : BaseViewModel() {

    var loadersList: MutableLiveData<Map<Exchange, DownloadState>> = MutableLiveData()

    private var currentDownloadsState: MutableMap<Exchange, DownloadState> = mutableMapOf()

    fun showExchanges() {
        val initialMap = mutableMapOf<Exchange, DownloadState>()
        loaders.keys.forEach { exchange ->
            initialMap[exchange] = DownloadState.AVAILABLE
        }
        currentDownloadsState = initialMap
        refreshExchangeList()
    }

    fun onExchangeClicked(exchange: Exchange) {
        logger.d("Downloading started for ${exchange.name} pairs")
        currentDownloadsState[exchange] = DownloadState.IN_PROGRESS

        launch {
            loaders[exchange]
                ?.fetchCurrencyPairs()
                ?.either({ handleLoadingError(it, exchange) }, { handleLoadingCompletion(exchange) })
        }
    }

    private fun handleLoadingError(failure: Failure, exchange: Exchange) {
        logger.d("Downloading error for ${exchange.name}")
        currentDownloadsState[exchange] = DownloadState.ERROR
        handleFailure(failure)
        refreshExchangeList()
    }

    private fun handleLoadingCompletion(exchange: Exchange) {
        logger.d("Downloading completed for ${exchange.name}")
        currentDownloadsState[exchange] = DownloadState.COMPLETED
        refreshExchangeList()
    }

    private fun refreshExchangeList() {
        loadersList.value = currentDownloadsState
    }
}
