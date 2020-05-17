package com.sedsoftware.core.presentation.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

class FragmentViewBinder<T : ViewBinding>(private val viewBindingClass: Class<T>) {

    private val bindViewMethod by lazy(LazyThreadSafetyMode.NONE) {
        viewBindingClass.getMethod("bind", View::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    fun bind(fragment: Fragment): T {
        return bindViewMethod(null, fragment.requireView()) as T
    }
}
