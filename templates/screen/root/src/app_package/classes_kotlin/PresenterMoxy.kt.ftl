package ${screenPackageName}.presenter

import ${coreUiPackageName}.base.BasePresenter
import ${screenPackageName}.view.${screenViewClass}

import com.arellomobile.mvp.InjectViewState

<#if cicerone>
import ru.terrakok.cicerone.Router
</#if>
import javax.inject.Inject

@InjectViewState
class ${screenPresenterClass} @Inject constructor(<#if cicerone>private val router: Router</#if>): BasePresenter<${screenViewClass}>() {

}
