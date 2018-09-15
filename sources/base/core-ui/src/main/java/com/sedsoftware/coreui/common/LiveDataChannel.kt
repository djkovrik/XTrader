package com.sedsoftware.coreui.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.experimental.channels.LinkedListChannel
import kotlinx.coroutines.experimental.channels.ReceiveChannel

// Src: https://github.com/dmytrodanylyk/coroutines-arch
class LiveDataChannel<T>(private val liveData: LiveData<T>) : LinkedListChannel<T?>(), ReceiveChannel<T?>,
    Observer<T?>, LifecycleObserver {

    override fun onChanged(t: T?) {
        offer(t)
    }

    override fun afterClose(cause: Throwable?) = liveData.removeObserver(this)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() = close()
}
