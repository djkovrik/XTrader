package ${screenPackageName}.view

import android.os.Bundle
import android.support.annotation.LayoutRes

import ${screenPackageName}.R
import ${coreUiPackageName}.base.BaseActivity
import ${screenPackageName}.view.${screenViewClass}
import ${screenPackageName}.presenter.${screenPresenterClass}
import ${screenPackageName}.di.${screenComponentClass}
import ${corePackageName}.${applicationInterface}
<#if cicerone>
import ru.terrakok.cicerone.Navigator
</#if>
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import javax.inject.Inject

class ${screenClass}: BaseActivity(), ${screenViewClass} {

    @Inject
    @InjectPresenter
    lateinit var presenter : ${screenPresenterClass}

    @ProvidePresenter
    fun providePresenter(): ${screenPresenterClass} = presenter

    <#if cicerone>
    override var navigator: Navigator = Navigator { }
    </#if>

    @LayoutRes
    override fun layoutRes() = R.layout.${screenLayoutName}

    override fun injectDependencies() {
        ${screenComponentClass}.Initializer
                .init((applicationContext as ${applicationInterface}).getAppComponent())
                .inject(this@${screenClass})
    }

    override fun viewCreated() {

    }
}
