package com.sedsoftware.coreutils.common

sealed class Success {
    object DownloadCompleted : Success()
    // etc.

    abstract class FeatureSuccess : Success()
}
