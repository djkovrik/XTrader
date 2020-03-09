package com.sedsoftware.core.di.management.internal

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject

internal class LifecycleCallbacksManager(
    private val storage: DaggerComponentStorage
) : ActivityLifecycleCallbacks, FragmentManager.FragmentLifecycleCallbacks() {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        if (activity is HasDaggerComponent<*>) {
            val key = activity.getComponentKey()
            if (storage.get(key) == null) {
                storage.add(key, activity.getComponent())
            }

            (activity as? FragmentActivity)?.supportFragmentManager
                ?.registerFragmentLifecycleCallbacks(this, true)
        }

        if (activity is HasInject) {
            activity.inject()
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        if (activity is HasDaggerComponent<*> && activity.isFinishing) {
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

        if (f is HasInject) {
            f.inject()
        }

        super.onFragmentPreCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        if (f is HasDaggerComponent<*>) {
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
