package ${coreUiPackageName}.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.arellomobile.mvp.MvpAppCompatActivity
import android.support.v4.app.Fragment
<#if cicerone>
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
</#if>

import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity() {

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewCreated()

    protected abstract fun injectDependencies()

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
}
