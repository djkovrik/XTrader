package ${screenPackageName}.view

import android.os.Bundle
import android.support.annotation.LayoutRes

import ${screenPackageName}.R
import ${coreUiPackageName}.base.BaseActivity
import ${screenPackageName}.view.${screenViewClass}
import ${screenPackageName}.presenter.${screenPresenterClass}
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @LayoutRes
    override fun layoutRes() = R.layout.${screenLayoutName}

    override fun viewCreated() {

    }
}
