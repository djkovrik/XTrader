package ${coreUiPackageName}.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {

}
