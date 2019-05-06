package com.sedsoftware.core.utils.common

sealed class Failure {
    object NetworkConnectionMissing : Failure()
    class ServerError(throwable: Throwable) : Failure()
    class LocalPersistenceError(throwable: Throwable) : Failure()
    // etc.

    abstract class FeatureFailure : Failure()
}
