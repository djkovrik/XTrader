package com.sedsoftware.core.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.core.utils.common.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import javax.inject.Provider
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    @Inject
    lateinit var factories: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<DestinationFactory>>

    var failure: MutableLiveData<Failure> = MutableLiveData()

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
