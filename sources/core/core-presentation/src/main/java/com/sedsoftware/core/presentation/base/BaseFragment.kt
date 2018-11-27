package com.sedsoftware.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.NavDirectionsFactory
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int
    abstract fun inject()

    protected val parentActivityComponent: MainActivityToolsProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as? ActivityComponentHolder)?.getActivityComponent()
                ?: throw RuntimeException("Parent activity must implement ActivityComponentHolder interface")
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navDirectionsFactory: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<NavDirectionsFactory>>

    override fun onAttach(context: Context?) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutId(), container, false)
}
