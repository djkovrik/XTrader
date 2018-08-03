package com.sedsoftware.core.repository

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.channels.produce

interface Repository {

    fun <T> produce(producer: suspend ProducerScope<T>.() -> Unit) = produce(CommonPool) {
        producer()
    }
}
