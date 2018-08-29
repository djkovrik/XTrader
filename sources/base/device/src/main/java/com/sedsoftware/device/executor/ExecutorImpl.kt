package com.sedsoftware.device.executor

import com.sedsoftware.core.common.Suspendable
import com.sedsoftware.core.executor.Executor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class ExecutorImpl @Inject constructor() : Executor {

    override fun <T> ui(func: Suspendable<T>): Job =
        launch(UI) {
            func()
        }

    override fun <T> bg(func: Suspendable<T>): Job =
        launch(CommonPool) {
            func()
        }

    override fun <T> bgWithResult(func: Suspendable<T>): Deferred<T> =
        async(CommonPool) {
            func()
        }
}
