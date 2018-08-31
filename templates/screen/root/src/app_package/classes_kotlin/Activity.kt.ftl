package ${screenPackageName}.view

import android.os.Bundle
import android.support.annotation.LayoutRes

import ${corePackageName}.base.BaseActivity
import ${screenPackageName}.R
import ${screenPackageName}.view.${screenViewClass}
import ${screenPackageName}.presenter.${screenPresenterClassClass}
<#if cicerone>
import ru.terrakok.cicerone.Navigator
</#if>

class ${screenViewClass}: BaseActivity<${screenPresenterClass}, ${screenViewClass}>(), ${screenViewClass} {

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
