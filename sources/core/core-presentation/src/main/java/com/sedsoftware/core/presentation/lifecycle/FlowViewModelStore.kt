package com.sedsoftware.core.presentation.lifecycle

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class FlowViewModelStore : ViewModelStoreOwner {

    private val flowViewModelStore: ViewModelStore = ViewModelStore()

    override fun getViewModelStore(): ViewModelStore = flowViewModelStore
}
