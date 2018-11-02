package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.device.api.executor.Executor
import com.sedsoftware.core.utils.common.Suspendable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

interface Interactor {

    val executor: Executor

    suspend fun <T> executeInBg(func: Suspendable<T>): Job =
        executor.bg(func)

    suspend fun <T> executeInBgWithResult(func: Suspendable<T>): Deferred<T> =
        executor.bgWithResult(func)
}
