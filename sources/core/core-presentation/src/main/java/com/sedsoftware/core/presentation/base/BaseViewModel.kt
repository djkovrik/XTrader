package com.sedsoftware.core.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedsoftware.core.utils.common.Failure
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    var viewModelFailure: MutableLiveData<Failure> = MutableLiveData()

    protected fun launch(block: () -> Unit) =
        viewModelScope.launch { block.invoke() }

    protected fun <T> async(block: () -> T): Deferred<T> =
        viewModelScope.async { block.invoke() }

    protected fun handleFailure(failure: Failure) {
        this.viewModelFailure.value = failure
    }
}
