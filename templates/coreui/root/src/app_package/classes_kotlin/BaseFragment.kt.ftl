package ${coreUiPackageName}.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View

import javax.inject.Inject

abstract class BaseFragment<P: BasePresenter<V>, V: BaseView>: Fragment() {

    @Inject lateinit var presenter: P

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun injectDependencies()

    override fun onAttach(context: Context?) {
        injectDependencies()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
      return inflater.inflate(layoutRes(), container, false)
     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onViewAttached(this as V)
        presenter.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.onStop()
        presenter.onViewDetached()
    }
}
