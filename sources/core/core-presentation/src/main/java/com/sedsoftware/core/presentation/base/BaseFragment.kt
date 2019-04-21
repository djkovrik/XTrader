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
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    protected val parentActivityComponent: MainActivityToolsProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as? ActivityToolsHolder)?.getActivityToolsProvider()
                ?: throw RuntimeException("Parent activity must implement ActivityComponentHolder interface")
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getLayoutResId(): Int

    abstract fun inject()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutResId(), container, false)

//    protected fun notify(@StringRes message: Int) =
//        XTSnackbar.make(rootView, BaseTransientBottomBar.LENGTH_SHORT)
//            .setText(string(message))
//            .show()
//
//    protected fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
//        XTSnackbar.make(rootView, BaseTransientBottomBar.LENGTH_SHORT)
//            .setText(string(message))
//            .setAction(string(actionText), View.OnClickListener { action.invoke() })
//            .show()
//    }
}
