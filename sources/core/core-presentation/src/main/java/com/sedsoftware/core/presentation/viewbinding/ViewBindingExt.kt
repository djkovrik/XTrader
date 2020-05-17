package com.sedsoftware.core.presentation.viewbinding

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : ViewBinding> Fragment.viewBinding(
    noinline viewBinder: (Fragment) -> T = FragmentViewBinder(T::class.java)::bind
): ReadOnlyProperty<Fragment, T> {
    return FragmentViewBindingProperty(viewBinder)
}
