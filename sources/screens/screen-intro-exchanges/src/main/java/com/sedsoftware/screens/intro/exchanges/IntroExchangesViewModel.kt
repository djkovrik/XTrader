package com.sedsoftware.screens.intro.exchanges

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.domain.navigation.FlowSwitcher
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.type.DownloadState
import com.sedsoftware.core.utils.extension.either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.screens.intro.exchanges.model.ExchangeListItem
import javax.inject.Inject

class IntroExchangesViewModel @Inject constructor(
    private val coordinator: FlowSwitcher,
    private val loaders: Map<Exchange, @JvmSuppressWildcards CurrencyPairLoader>
) : BaseViewModel() {

    internal val exchangeListLiveData = MutableLiveData<List<ExchangeListItem>>()
    internal val nextButtonAvailableLiveData = MutableLiveData<Boolean>()

    private val loadingStates = mutableMapOf<Exchange, DownloadState>()

    init {
        loaders.keys.forEach { exchange ->
            loadingStates[exchange] = DownloadState.AVAILABLE
        }
        emitCurrentList()
    }

    fun switchToRegularFlow() {
        coordinator.switchToRegularFlow()
    }

    fun onExchangeClicked(exchange: Exchange) {
        setExchangeState(exchange, DownloadState.IN_PROGRESS)
        handleNextButtonState()

        launch {
            loaders[exchange]
                ?.fetchCurrencyPairs()
                ?.either({ handleLoadingError(it, exchange) }, { handleLoadingCompletion(exchange) })
        }
    }

    private fun setExchangeState(exchange: Exchange, state: DownloadState) {
        loadingStates[exchange] = state
        emitCurrentList()
    }

    private fun emitCurrentList() {
        exchangeListLiveData.value = loadingStates.map {
            ExchangeListItem(
                it.key,
                it.value
            )
        }
    }

    private fun handleLoadingError(failure: Failure, exchange: Exchange) {
        setExchangeState(exchange, DownloadState.ERROR)
        handleFailure(failure)
    }

    private fun handleLoadingCompletion(exchange: Exchange) {
        setExchangeState(exchange, DownloadState.COMPLETED)
        handleNextButtonState()
    }

    private fun handleNextButtonState() {
        // At least one completed and no in progress
        nextButtonAvailableLiveData.value =
            loadingStates.values.contains(DownloadState.COMPLETED) && !loadingStates.values.contains(DownloadState.IN_PROGRESS)
    }
}
