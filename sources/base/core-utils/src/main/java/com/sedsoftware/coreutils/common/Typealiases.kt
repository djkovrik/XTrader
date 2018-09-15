package com.sedsoftware.coreutils.common

import kotlinx.coroutines.experimental.channels.ReceiveChannel

typealias Suspendable<T> = suspend () -> T
typealias Producer<T> = ReceiveChannel<T>
