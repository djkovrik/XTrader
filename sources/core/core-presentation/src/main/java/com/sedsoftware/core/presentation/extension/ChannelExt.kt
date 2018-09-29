package com.sedsoftware.core.presentation.extension

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consume
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

// Src: https://github.com/dmytrodanylyk/coroutines-arch
fun <E> ReceiveChannel<E>.launchConsumeEach(
    context: CoroutineContext = DefaultDispatcher,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    parent: Job? = null,
    action: (E) -> Unit
) = launch(context, start, parent) {
    consume {
        for (element in this) action(element)
    }
}
