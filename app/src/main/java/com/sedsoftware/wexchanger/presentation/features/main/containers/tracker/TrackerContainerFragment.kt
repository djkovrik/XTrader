package com.sedsoftware.wexchanger.presentation.features.main.containers.tracker

import android.content.Context
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
  fun providePresenter(): TrackerContainerPresenter =
    Toothpick
      .openScope(AppScope.TAB_TRACKER)
      .getInstance(TrackerContainerPresenter::class.java)

  override fun onAttach(context: Context?) {
    Toothpick
      .openScopes(AppScope.APPLICATION, AppScope.TAB_TRACKER)
      .apply {
        installModules(TrackerContainerModule(this@TrackerContainerFragment))
        Toothpick.inject(this@TrackerContainerFragment, this)
      }
      .also { Toothpick.closeScope(AppScope.TAB_TRACKER) }

    super.onAttach(context)
  }

  override fun onDestroyView() {
    Toothpick.closeScope(AppScope.TAB_TRACKER)
    super.onDestroyView()
  }
}
