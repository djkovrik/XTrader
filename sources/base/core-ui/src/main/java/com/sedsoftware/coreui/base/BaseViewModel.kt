package com.sedsoftware.coreui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedsoftware.coreutils.common.Failure

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
