package com.sedsoftware.core.common

sealed class Success {
    object DownloadCompleted : Success()
    // etc.

    abstract class FeatureSuccess: Success()
}
