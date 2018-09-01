package ${coreUiPackageName}.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.annotation.LayoutRes

import javax.inject.Inject
<#if cicerone>

import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
</#if>

abstract class BaseActivity<P: BasePresenter<V>, V: BaseView>: AppCompatActivity() {
  
    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewCreated()

    protected abstract fun injectDependencies()

    @Inject
    lateinit var presenter: P
    <#if cicerone>

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    abstract var navigator: Navigator
    </#if>

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        viewCreated()

        presenter.onViewAttached(this as V)
        presenter.onStart()
    }
    <#if cicerone>

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
    </#if>

    override fun onStop() {
        super.onStop()
        if (isFinishing) {
            presenter.onStop()
            presenter.onViewDetached()
        }
    }
}
