package com.sedsoftware.core.utils.common

sealed class Success {
    object DownloadCompleted : Success()
    // etc.

    abstract class FeatureSuccess : Success()
}
