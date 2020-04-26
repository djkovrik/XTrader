package com.sedsoftware.core.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sedsoftware.core.domain.errorhandler.CanShowError
import com.sedsoftware.core.presentation.CanHandleBackPressed
import com.sedsoftware.core.presentation.delegate.SnackbarDelegate

abstract class BaseFragment : Fragment(), CanShowError {

    private lateinit var backPressHandler: CanHandleBackPressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity is CanHandleBackPressed) {
            backPressHandler = activity as CanHandleBackPressed
        } else {
            throw ClassCastException("Base activity must implement BackPressHandler interface")
        }
    }

    override fun onStart() {
        super.onStart()
        backPressHandler.setSelectedFragment(this)
    }

    override fun showMessage(text: String) {
        (activity as? SnackbarDelegate)?.notifyOnTop(text)
    }

    open fun onBackPressed(): Boolean = false
}
