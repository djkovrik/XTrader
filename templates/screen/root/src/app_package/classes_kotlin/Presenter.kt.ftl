package ${screenPackageName}.presenter

import ${coreUiPackageName}.base.BasePresenterImpl
import ${screenPackageName}.view.${viewClass}

import javax.inject.Inject
<#if cicerone>

import ru.terrakok.cicerone.Router
</#if>

class ${presenterClass} @Inject constructor(<#if cicerone>private val router: Router</#if>): BasePresenterImpl<${viewClass}>() {

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

}