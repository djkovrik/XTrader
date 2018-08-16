package com.sedsoftware.core.common

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object ParsingError : Failure()
    // etc.

    abstract class FeatureFailure: Failure()
}
