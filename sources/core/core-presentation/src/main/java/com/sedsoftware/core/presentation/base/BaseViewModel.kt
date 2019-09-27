package com.sedsoftware.core.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.SingleEvent

abstract class BaseViewModel : ViewModel() {

    var viewModelFailure: MutableLiveData<SingleEvent<Failure>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.viewModelFailure.value = event(failure)
    }

    fun <T> BaseViewModel.event(data: T): SingleEvent<T> = SingleEvent(data)
}
