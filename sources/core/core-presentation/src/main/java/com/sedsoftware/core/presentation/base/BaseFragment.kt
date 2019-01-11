package com.sedsoftware.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.Router
import com.sedsoftware.core.navigation.destination.DestinationFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import javax.inject.Provider
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment(), CoroutineScope {

    abstract val layoutResId: Int

    abstract fun inject()

    lateinit var job: Job

    protected val parentActivityComponent: MainActivityToolsProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as? ActivityToolsHolder)?.getActivityToolsProvider()
                ?: throw RuntimeException("Parent activity must implement ActivityComponentHolder interface")
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var destinationFactories: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<DestinationFactory>>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutResId, container, false)

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
