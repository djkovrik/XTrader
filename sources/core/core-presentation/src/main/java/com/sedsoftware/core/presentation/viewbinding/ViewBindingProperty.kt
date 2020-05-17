package com.sedsoftware.core.presentation.viewbinding

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.sedsoftware.core.tools.impl.checkIsMainThread
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class ViewBindingProperty<R, T : ViewBinding>(
    private val viewBinder: (R) -> T
) : ReadOnlyProperty<R, T> {

    internal var viewBinding: T? = null
    private val lifecycleObserver = BindingLifecycleObserver()

    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

    @MainThread
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        checkIsMainThread("ViewBindingProperty")
        viewBinding?.let { return it }

        getLifecycleOwner(thisRef).lifecycle.addObserver(lifecycleObserver)
        return viewBinder(thisRef).also { viewBinding = it }
    }

    private inner class BindingLifecycleObserver : DefaultLifecycleObserver {

        private val mainHandler = Handler(Looper.getMainLooper())

        @MainThread
        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            mainHandler.post {
                viewBinding = null
            }
        }
    }
}
