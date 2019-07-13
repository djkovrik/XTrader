package com.sedsoftware.core.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedsoftware.core.utils.type.Failure

abstract class BaseViewModel : ViewModel() {

    var viewModelFailure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.viewModelFailure.value = failure
    }
}
