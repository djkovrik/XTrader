package ${coreUiPackageName}.base

abstract class BasePresenterImpl<V: BaseView>: BasePresenter<V> {
    
    override var view: V? = null

    override fun onViewAttached(view: V) {
        this.view = view
    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onViewDetached() {
        view = null
    }
}
