package com.sedsoftware.core.device.impl.executor

import com.sedsoftware.core.device.api.executor.Executor
import com.sedsoftware.core.utils.common.Suspendable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppExecutor @Inject constructor() : Executor {

    override suspend fun <T> bg(func: Suspendable<T>): Job =
        coroutineScope {
            launch {
                func()
            }
        }

    override suspend fun <T> bgWithResult(func: Suspendable<T>): Deferred<T> =
        coroutineScope {
            async {
                func()
            }
        }
}
