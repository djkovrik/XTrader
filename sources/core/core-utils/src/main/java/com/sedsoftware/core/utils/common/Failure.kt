package com.sedsoftware.core.utils.common

sealed class Failure {
    object ServerError : Failure()
    // etc.

    abstract class FeatureFailure : Failure()
}
