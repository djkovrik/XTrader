package ${screenPackageName}.view

import android.os.Bundle
import android.support.annotation.LayoutRes

import ${coreUiPackageName}.base.BaseActivity
import ${screenPackageName}.R
import ${screenPackageName}.view.${screenViewClass}
import ${screenPackageName}.presenter.${screenPresenterClassClass}
import ${screenPackageName}.di.${screenComponentClass}
import ${corePackageName}.${applicationInterface}
<#if cicerone>
import ru.terrakok.cicerone.Navigator
</#if>

class ${screenClass}: BaseActivity<${screenPresenterClass}, ${screenViewClass}>(), ${screenViewClass} {

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
