package com.sedsoftware.core.di.management

import android.app.Application
import com.sedsoftware.core.di.management.internal.DaggerComponentStorage
import com.sedsoftware.core.di.management.internal.LifecycleCallbacksManager

object DaggerComponentManager {

    private val storage = DaggerComponentStorage()
    private val callbacksManager = LifecycleCallbacksManager(storage)

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(callbacksManager)
    }

    fun find(predicate: (Any?) -> Boolean): Any? =
        storage.values.find { predicate(it) }

    fun get(key: String): Any? =
        storage.get(key) ?: error("Component for $key not found")
}
