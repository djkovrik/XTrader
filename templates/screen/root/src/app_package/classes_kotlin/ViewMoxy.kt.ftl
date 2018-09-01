package ${screenPackageName}.view

import ${coreUiPackageName}.base.BaseView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ${screenViewClass}: BaseView {

}
