package com.sedsoftware.core.di.management

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

object DaggerComponentManager {

    private val callbackManager = LifecycleCallbackManager()
    private val storage = DaggerComponentStorage()

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(callbackManager)
    }

    fun find(predicate: (Any?) -> Boolean): Any? =
        storage.values.find { predicate(it) }

    class LifecycleCallbackManager : ActivityLifecycleCallbacks, FragmentManager.FragmentLifecycleCallbacks() {

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            if (activity is HasDaggerComponent<*>) {
                val key = activity.getComponentKey()
                if (storage.get(key) == null) {
                    storage.add(key, activity.getComponent())
                }

                (activity as? FragmentActivity)?.supportFragmentManager
                    ?.registerFragmentLifecycleCallbacks(this, true)

                activity.inject()
            }
        }

        override fun onActivityDestroyed(activity: Activity?) {
            if (activity is HasDaggerComponent<*> && activity.isFinishing) {
                activity.onComponentDestroyed()
                storage.remove(activity.getComponentKey())
            }
        }

        override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            if (f is HasDaggerComponent<*>) {
                val key = f.getComponentKey()
                if (storage.get(key) == null) {
                    storage.add(key, f.getComponent())
                }
            }

            super.onFragmentPreCreated(fm, f, savedInstanceState)

            (f as? HasDaggerComponent<*>)?.inject()
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            if (f is HasDaggerComponent<*>) {
                f.onComponentDestroyed()
                storage.remove(f.getComponentKey())
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
