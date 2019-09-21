package com.sedsoftware.core.utils.type

sealed class Success {
    object CurrencyMapLoadingCompleted : Success()
    object PairsLoadingCompleted : Success()
}
