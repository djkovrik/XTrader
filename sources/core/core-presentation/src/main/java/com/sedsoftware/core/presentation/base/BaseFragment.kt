package com.sedsoftware.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.di.provider.ActivityToolsProvider
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract fun inject()
    abstract fun getLayoutId(): Int

    protected val parentActivityComponent: ActivityToolsProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as? ActivityComponentHolder)?.getActivityComponent()
                ?: throw RuntimeException("Parent activity must implement ActivityComponentHolder interface")
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
