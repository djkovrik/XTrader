package com.sedsoftware.core.presentation.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.sedsoftware.core.domain.errorhandler.CanShowError
import com.sedsoftware.core.presentation.delegate.SnackbarDelegate

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId), CanShowError {

    override fun showMessage(text: String) {
        (activity as? SnackbarDelegate)?.notifyOnTop(text)
    }
}
