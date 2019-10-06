package com.sedsoftware.core.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.SingleEvent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    protected val appComponent: AppProvider
        get() = (requireActivity().applicationContext as App).getAppComponent()

    @Inject
    lateinit var storeOwner: ViewModelStoreOwner

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getLayoutResId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutResId(), container, false)

    protected fun observeFailures(failureEvent: SingleEvent<Failure>?) {
//        failureEvent?.getIfNotHandled()?.let { failure ->
//            when (failure) {
//                is NetworkConnectionMissing -> notifyTop(string(R.string.msg_no_internet_connection))
//                is PairsLoadingError -> notifyTop(string(R.string.msg_server_error, failure.throwable.message))
//            }
//        }
    }
}
