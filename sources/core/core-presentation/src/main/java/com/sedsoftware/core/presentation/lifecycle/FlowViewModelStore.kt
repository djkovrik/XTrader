package com.sedsoftware.core.presentation.lifecycle

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import javax.inject.Inject

class FlowViewModelStore @Inject constructor() : ViewModelStoreOwner {

    private val flowViewModelStore: ViewModelStore = ViewModelStore()

    override fun getViewModelStore(): ViewModelStore = flowViewModelStore
}
