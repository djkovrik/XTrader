package com.sedsoftware.core.utils.common

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    // etc.

    abstract class FeatureFailure : Failure()
}
