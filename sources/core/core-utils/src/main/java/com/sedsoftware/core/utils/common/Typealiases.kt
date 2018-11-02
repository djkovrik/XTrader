package com.sedsoftware.core.utils.common

import kotlinx.coroutines.channels.ReceiveChannel

typealias Suspendable<T> = suspend () -> T
typealias Producer<T> = ReceiveChannel<T>
