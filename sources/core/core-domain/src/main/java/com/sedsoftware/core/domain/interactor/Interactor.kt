package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.device.api.Executor
import com.sedsoftware.core.utils.common.Suspendable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

interface Interactor {

    val executor: Executor

    suspend fun <T> runInBackground(func: Suspendable<T>): Job =
        executor.executeInBackground(func)

    suspend fun <T> runInBackgroundWithResult(func: Suspendable<T>): Deferred<T> =
        executor.executeInBackgroundWithResult(func)
}
