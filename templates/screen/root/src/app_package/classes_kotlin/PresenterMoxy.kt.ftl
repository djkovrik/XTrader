package ${screenPackageName}.presenter

import ${screenPackageName}.view.${screenViewClass}

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

<#if cicerone>
import ru.terrakok.cicerone.Router
</#if>
import javax.inject.Inject

@InjectViewState
class ${screenPresenterClass} @Inject constructor(<#if cicerone>private val router: Router</#if>): MvpPresenter<${screenViewClass}>() {

}
