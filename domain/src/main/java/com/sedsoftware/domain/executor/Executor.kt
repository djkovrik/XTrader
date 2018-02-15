package com.sedsoftware.domain.executor

import com.sedsoftware.domain.common.Suspendable
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job

interface Executor {

  fun <T> ui(uiFunc: Suspendable<T>): Job

  fun <T> bg(bgFunc: Suspendable<T>): Deferred<T>
}
