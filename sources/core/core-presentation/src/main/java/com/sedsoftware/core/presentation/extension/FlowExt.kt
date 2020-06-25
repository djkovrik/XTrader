package com.sedsoftware.core.presentation.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull as mapNotNullFlow

inline fun <T, R : Any> Flow<T>.mapNotNull(crossinline mapper: (T) -> R?): Flow<R> =
    mapNotNullFlow { mapper(it) }
