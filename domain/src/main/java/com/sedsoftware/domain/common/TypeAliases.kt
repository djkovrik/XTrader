package com.sedsoftware.domain.common

typealias Callback<T> = (T) -> Unit
typealias Suspendable<T> = suspend () -> T
