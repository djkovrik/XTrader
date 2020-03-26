package com.sedsoftware.core.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sedsoftware.core.presentation.CanHandleBackPressed
import com.sedsoftware.core.presentation.delegate.SnackbarDelegate

abstract class BaseFragment : Fragment() {

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

    open fun onBackPressed(): Boolean = false

    private fun notifyTop(message: String) {
        (activity as? SnackbarDelegate)?.notifyOnTop(message)
    }
}
