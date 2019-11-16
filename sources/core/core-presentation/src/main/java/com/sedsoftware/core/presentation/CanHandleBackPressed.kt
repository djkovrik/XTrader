package com.sedsoftware.core.presentation

import com.sedsoftware.core.presentation.base.BaseFragment

interface CanHandleBackPressed {
    fun setSelectedFragment(fragment: BaseFragment)
}
