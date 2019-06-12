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
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
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

    abstract fun getLayoutResId(): Int

    abstract fun inject()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutResId(), container, false)

    protected fun notifyTop(message: String) {
        snackbarDelegate.notifyOnTop(message)
    }
}
