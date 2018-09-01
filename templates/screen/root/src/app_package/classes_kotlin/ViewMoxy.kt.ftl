package ${screenPackageName}.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arellomobile.mvp.MvpView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ${screenViewClass}: MvpView {

}
