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

    inline fun <reified T> get(): T {
        val predicate = object : (Any?) -> Boolean {
            override fun invoke(component: Any?): Boolean = component is T

            override fun toString(): String = T::class.java.simpleName
        }

        return find(predicate) as T
    }

    fun find(predicate: (Any?) -> Boolean): Any =
        storage.find(predicate) ?: error("Component not found")
}
