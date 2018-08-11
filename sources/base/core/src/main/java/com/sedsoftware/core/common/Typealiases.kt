package com.sedsoftware.core.common

import kotlinx.coroutines.experimental.channels.ReceiveChannel

typealias ErrorCallback = (Throwable) -> Unit
typealias SuccessCallback<T> = (T) -> Unit
typealias Suspendable<T> = suspend () -> T
typealias Producer<T> = ReceiveChannel<T>
