package com.sedsoftware.core.device.api.executor

import com.sedsoftware.core.utils.common.Suspendable
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job

interface Executor {

    fun <T> ui(func: Suspendable<T>): Job

    fun <T> bg(func: Suspendable<T>): Job

    fun <T> bgWithResult(func: Suspendable<T>): Deferred<T>
}
