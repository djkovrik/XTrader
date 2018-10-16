package com.sedsoftware.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.provider.AppProvider
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int
    abstract fun inject()

    protected val appComponent: AppProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.applicationContext as App).getAppComponent()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context?) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutId(), container, false)
}
