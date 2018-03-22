package com.sedsoftware.wexchanger.presentation.features.main.containers.tracker

import androidx.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.TrackerContainerModule
import ru.terrakok.cicerone.Navigator
import toothpick.Toothpick

@Layout(R.layout.fragment_tab_container)
class TrackerContainerFragment : BaseContainerFragment(), TrackerContainerView {

  companion object {
    fun newInstance(tag: String?) = TrackerContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }

  override val localNavigator: Navigator
    get() =
      Toothpick
        .openScope(AppScope.TAB_TRACKER)
        .getInstance(Navigator::class.java)

  @InjectPresenter
  lateinit var presenter: TrackerContainerPresenter

  @ProvidePresenter
  fun providePresenter(): TrackerContainerPresenter {
    val scope = Toothpick.openScopes(AppScope.APPLICATION, AppScope.TAB_TRACKER)
    scope.installModules(TrackerContainerModule(this))
    Toothpick.inject(this, scope)
    return scope.getInstance(TrackerContainerPresenter::class.java)
  }
}
