package com.sedsoftware.core.presentation.viewbinding

import android.view.View
import androidx.viewbinding.ViewBinding

interface ViewBinder<T : ViewBinding> {
    fun bind(view: View): T
}

internal inline fun <T : ViewBinding> viewBinder(crossinline bindView: (View) -> T): ViewBinder<T> {
    return object : ViewBinder<T> {
        override fun bind(view: View) = bindView(view)
    }
}

class DefaultViewBinder<T : ViewBinding>(
    private val viewBindingClass: Class<T>
) : ViewBinder<T> {

    private val bindViewMethod by lazy(LazyThreadSafetyMode.NONE) {
        viewBindingClass.getMethod("bind", View::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    override fun bind(view: View): T {
        return bindViewMethod(null, view) as T
    }
}
