package com.sedsoftware.coredeviceimpl.executor

import com.sedsoftware.coredeviceapi.executor.Executor
import com.sedsoftware.coreutils.common.Suspendable
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppExecutor @Inject constructor() : Executor {

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
