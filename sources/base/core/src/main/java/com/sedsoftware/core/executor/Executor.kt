package com.sedsoftware.core.executor

import com.sedsoftware.core.common.Suspendable
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job

interface Executor {

    fun <T> ui(func: Suspendable<T>): Job

    fun <T> bg(func: Suspendable<T>): Job

    fun <T> bgWithResult(func: Suspendable<T>): Deferred<T>
}
