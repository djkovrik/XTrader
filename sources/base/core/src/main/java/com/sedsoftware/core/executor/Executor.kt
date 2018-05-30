package com.sedsoftware.core.executor

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job

interface Executor {

  fun <T> ui(uiFunc: suspend () -> T): Job

  fun <T> bg(bgFunc: suspend () -> T): Deferred<T>
}
