package com.sedsoftware.core.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.presentation.CanHandleBackPressed
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.NetworkConnectionMissing
import com.sedsoftware.core.utils.type.Failure.PairsLoadingError
import com.sedsoftware.core.utils.type.SingleEvent

abstract class BaseFragment : Fragment() {

    abstract val layoutResId: Int

    private lateinit var backPressHandler: CanHandleBackPressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity is CanHandleBackPressed) {
            backPressHandler = activity as CanHandleBackPressed
        } else {
            throw ClassCastException("Base activity must implement BackPressHandler interface")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutResId, container, false)

    override fun onStart() {
        super.onStart()
        backPressHandler.setSelectedFragment(this)
    }

    open fun onBackPressed(): Boolean = false

    protected fun observeFailures(failureEvent: SingleEvent<Failure>?) {
        failureEvent?.getIfNotHandled()?.let { failure ->
            when (failure) {
                is NetworkConnectionMissing -> notifyTop(string(R.string.msg_no_internet_connection))
                is PairsLoadingError -> notifyTop(string(R.string.msg_server_error, failure.throwable.message))
            }
        }
    }

    private fun notifyTop(message: String) {
        (activity as? SnackbarDelegate)?.notifyOnTop(message)
    }
}
