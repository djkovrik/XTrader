package com.sedsoftware.core.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.SingleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<SingleEvent<Failure>>> LifecycleOwner.failure(liveData: L, body: (SingleEvent<Failure>) -> Unit) =
    liveData.observe(this, Observer(body))

fun LifecycleOwner.launch(block: suspend CoroutineScope.() -> Unit) =
    lifecycleScope.launch { block.invoke(this) }

fun <T> LifecycleOwner.async(block: suspend CoroutineScope.() -> T): Deferred<T> =
    lifecycleScope.async { block.invoke(this) }
