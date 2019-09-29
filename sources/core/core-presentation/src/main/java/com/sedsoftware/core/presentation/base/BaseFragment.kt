package com.sedsoftware.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.MainActivityToolsProvider
import com.sedsoftware.core.presentation.CanHandleBackPressed
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.NetworkConnectionMissing
import com.sedsoftware.core.utils.type.Failure.PairsLoadingError
import com.sedsoftware.core.utils.type.SingleEvent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    protected val parentActivityComponent: MainActivityToolsProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as? ActivityToolsHolder)?.getActivityToolsProvider()
            ?: throw UnsupportedOperationException("Parent activity must implement ActivityToolsHolder interface")
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var snackbarDelegate: SnackbarDelegate

    private lateinit var backPressedHandler: CanHandleBackPressed

    abstract fun getLayoutResId(): Int

    abstract fun inject()

    open fun onBackPressed(): Boolean = false

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)

        if (activity is CanHandleBackPressed) {
            backPressedHandler = activity as CanHandleBackPressed
        } else {
            throw ClassCastException("BaseActivity must implement BackPressHandler interface.")
        }
    }

    override fun onStart() {
        super.onStart()
        backPressedHandler.setSelectedFragment(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutResId(), container, false)

    protected fun observeFailures(failureEvent: SingleEvent<Failure>?) {
        failureEvent?.getIfNotHandled()?.let { failure ->
            when (failure) {
                is NetworkConnectionMissing -> notifyTop(string(R.string.msg_no_internet_connection))
                is PairsLoadingError -> notifyTop(string(R.string.msg_server_error, failure.throwable.message))
            }
        }
    }

    protected fun notifyTop(message: String) {
        snackbarDelegate.notifyOnTop(message)
    }
}
