package com.sedsoftware.device.executor

import com.sedsoftware.core.executor.Executor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class ThreadExecutor @Inject constructor() : Executor {

    override fun <T> ui(uiFunc: suspend () -> T): Job =
        launch(UI) {
            uiFunc()
        }

    override fun <T> bg(bgFunc: suspend () -> T): Deferred<T> =
        async(CommonPool) {
            bgFunc()
        }
}
