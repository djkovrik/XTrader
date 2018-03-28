package com.sedsoftware.data.executor

import com.sedsoftware.domain.executor.Executor
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
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
