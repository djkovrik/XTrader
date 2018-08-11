package com.sedsoftware.core.interactor

import com.sedsoftware.core.common.Suspendable
import com.sedsoftware.core.executor.Executor
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job

interface Interactor {

    val executor: Executor

    fun <T> executeForUi(func: Suspendable<T>): Job =
        executor.ui(func)

    fun <T> executeInBg(func: Suspendable<T>): Job =
        executor.bg(func)

    fun <T> executeInBgWithResult(func: Suspendable<T>): Deferred<T> =
        executor.bgWithResult(func)
}
