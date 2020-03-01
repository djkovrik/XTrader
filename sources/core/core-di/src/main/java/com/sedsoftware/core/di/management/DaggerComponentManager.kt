package com.sedsoftware.core.di.management

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

object DaggerComponentManager {

    private val components = mutableMapOf<String, Any?>()
    private val callbacksManager = LifecycleCallbacksManager()

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(callbacksManager)
    }

    fun find(predicate: (Any?) -> Boolean): Any? =
        components.values.find { predicate(it) }

    fun get(key: String): Any =
        components[key] ?: error("Component with $key not found")

    private fun add(key: String, component: Any?) {
        components[key] = component
    }

    private fun remove(key: String) {
        components.remove(key)
    }

    class LifecycleCallbacksManager : ActivityLifecycleCallbacks, FragmentManager.FragmentLifecycleCallbacks() {

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            if (activity is HasDaggerComponent<*>) {
                val key = activity.getComponentKey()
                if (components[key] == null) {
                    add(key, activity.getComponent())
                }

                (activity as? FragmentActivity)?.supportFragmentManager
                    ?.registerFragmentLifecycleCallbacks(this, true)

                activity.inject()
            }
        }

        override fun onActivityDestroyed(activity: Activity?) {
            if (activity is HasDaggerComponent<*> && activity.isFinishing) {
                activity.onComponentDestroyed()
                remove(activity.getComponentKey())
            }
        }

        override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            if (f is HasDaggerComponent<*>) {
                val key = f.getComponentKey()
                if (components[key] == null) {
                    add(key, f.getComponent())
                }
            }

            super.onFragmentPreCreated(fm, f, savedInstanceState)

            (f as? HasDaggerComponent<*>)?.inject()
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            if (f is HasDaggerComponent<*>) {
                f.onComponentDestroyed()
                remove(f.getComponentKey())
            }

            super.onFragmentDestroyed(fm, f)
        }

        // Dummy implementations
        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) = Unit

        override fun onActivityStarted(activity: Activity?) = Unit

        override fun onActivityResumed(activity: Activity?) = Unit

        override fun onActivityPaused(activity: Activity?) = Unit

        override fun onActivityStopped(activity: Activity?) = Unit
    }
}
