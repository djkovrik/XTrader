package ${coreUiPackageName}.base

interface BasePresenter<V: BaseView> {

    var view: V?

    fun onViewAttached(view: V)

    fun onStart()

    fun onStop()

    fun onViewDetached()
}
