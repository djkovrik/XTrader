package com.sedsoftware.core.interactor

import com.sedsoftware.core.executor.Executor

interface Interactor {

    val executor: Executor

    fun <T> postExecute(uiFun: suspend () -> T) =
        executor.ui(uiFun)

    fun <T> bgExecute(bgFun: suspend () -> T) =
        executor.bg(bgFun)
}
