package com.sedsoftware.core.utils.common

sealed class Success {
    object PairsLoadingCompleted : Success()
    // etc.

    abstract class FeatureSuccess : Success()
}
