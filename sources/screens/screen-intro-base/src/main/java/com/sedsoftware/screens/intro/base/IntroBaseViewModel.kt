package com.sedsoftware.screens.intro.base

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.interactor.CurrencyMapLoader
import com.sedsoftware.core.domain.navigation.StartingFlowCoordinator
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.utils.extension.either
import com.sedsoftware.core.utils.type.Success
import com.sedsoftware.core.utils.type.Success.CurrencyMapLoadingCompleted
import javax.inject.Inject

class IntroBaseViewModel @Inject constructor(
    private val coordinator: StartingFlowCoordinator,
    private val currencyMapLoader: CurrencyMapLoader
) : BaseViewModel() {

    internal val downloadingCompleted = MutableLiveData<Boolean>(false)

    init {
        launch {
            currencyMapLoader
                .isLoadingNeeded()
                .either(::handleFailure, ::handleMapCheck)
        }
    }

    fun downloadCurrencyMap() {
        launch {
            currencyMapLoader
                .loadCurrencyMap()
                .either(::handleFailure, ::handleSuccess)
        }
    }

    fun navigateToExchanges() {
        coordinator.navigateToExchangeScreen()
    }

    private fun handleSuccess(downloadSuccess: Success) {
        if (downloadSuccess is CurrencyMapLoadingCompleted) {
            downloadingCompleted.value = true
        }
    }

    private fun handleMapCheck(loadingNeeded: Boolean) {
        if (!loadingNeeded) {
            navigateToExchanges()
        }
    }
}
