package com.sedsoftware.domain.interactor

import com.sedsoftware.domain.executor.Executor

abstract class UseCase(private val executor: Executor) {

  fun <T> postExecute(uiFun: suspend () -> T) =
    executor.ui(uiFun)

  fun <T> bgExecute(bgFun: suspend () -> T) =
    executor.bg(bgFun)
}
