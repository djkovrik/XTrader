package com.sedsoftware.core.device.api.executor

import com.sedsoftware.core.utils.common.Suspendable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

interface Executor {

    suspend fun <T> bg(func: Suspendable<T>): Job

    suspend fun <T> bgWithResult(func: Suspendable<T>): Deferred<T>
}
