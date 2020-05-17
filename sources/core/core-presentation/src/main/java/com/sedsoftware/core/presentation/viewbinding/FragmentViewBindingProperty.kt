package com.sedsoftware.core.presentation.viewbinding

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

class FragmentViewBindingProperty<T : ViewBinding>(
    viewBinder: (Fragment) -> T
) : ViewBindingProperty<Fragment, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: Fragment) = thisRef.viewLifecycleOwner
}
