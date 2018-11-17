package com.sedsoftware.core.domain.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope

@ExperimentalCoroutinesApi
interface Repository {

    suspend fun <T> produceValues(producer: suspend ProducerScope<T>.() -> Unit) =
        coroutineScope {
            produce<T> {
                producer()
            }
        }
}
