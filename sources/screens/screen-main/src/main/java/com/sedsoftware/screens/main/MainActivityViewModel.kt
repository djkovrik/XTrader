package com.sedsoftware.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.navigation.NavigationFlow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(settings: Settings) : BaseViewModel() {

    companion object {

        internal val introNavGraph = R.navigation.navigation_intro

        internal val pinNavGraph = R.navigation.navigation_pin

        internal val mainNavGraphs: List<Int> = listOf(
                R.navigation.navigation_wallet,
                R.navigation.navigation_orders,
                R.navigation.navigation_market,
                R.navigation.navigation_tracker,
                R.navigation.navigation_tools
        )
    }

    internal var currentNavController = MutableLiveData<NavController>()
    internal var currentNavFlow = MutableLiveData<NavigationFlow>()

    internal var controller: NavController?
        get() = currentNavController.value
        set(value) {
            currentNavController.value = value
        }

    internal var flow: NavigationFlow?
        get() = currentNavFlow.value
        set(value) {
            currentNavFlow.value = value
        }

    init {
        currentNavFlow.value = when {
            settings.isPinEnabled -> NavigationFlow.PIN
            settings.isExchangesDownloaded -> NavigationFlow.MAIN
            else -> NavigationFlow.INTRO
        }
    }
}
