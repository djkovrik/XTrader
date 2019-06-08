package com.sedsoftware.core.utils.common

sealed class Failure {
    object NetworkConnectionMissing : Failure()
    class ServerError(val throwable: Throwable) : Failure()
    class LocalPersistenceError(val throwable: Throwable) : Failure()
    // etc.

    abstract class FeatureFailure : Failure()
}
