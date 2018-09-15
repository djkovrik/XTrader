package com.sedsoftware.coreapi.interactor

import com.sedsoftware.coreapi.executor.Executor
import com.sedsoftware.coreutils.common.Suspendable
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
