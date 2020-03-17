package com.sedsoftware.core.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun LifecycleOwner.launch(block: suspend CoroutineScope.() -> Unit) =
    lifecycleScope.launch { block.invoke(this) }

fun <T> LifecycleOwner.async(block: suspend CoroutineScope.() -> T): Deferred<T> =
    lifecycleScope.async { block.invoke(this) }
