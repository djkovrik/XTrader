package com.sedsoftware.core.tools.api

import com.sedsoftware.core.utils.common.Suspendable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

interface Executor {
    suspend fun <T> executeInBackground(func: Suspendable<T>): Job
    suspend fun <T> executeInBackgroundWithResult(func: Suspendable<T>): Deferred<T>
}
