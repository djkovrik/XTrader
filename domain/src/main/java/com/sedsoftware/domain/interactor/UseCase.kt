package com.sedsoftware.domain.interactor

import com.sedsoftware.domain.common.Suspendable
import com.sedsoftware.domain.executor.Executor

abstract class UseCase(private val executor: Executor) {

  fun <T> postExecute(uiFun: Suspendable<T>) =
      executor.ui(uiFun)

  fun <T> bgExecute(bgFun: Suspendable<T>) =
      executor.bg(bgFun)
}
