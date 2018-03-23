package com.sedsoftware.wexchanger.presentation.features.main.containers.wallet

import android.content.Context
import androidx.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.WalletContainerModule
import ru.terrakok.cicerone.Navigator
import toothpick.Toothpick

@Layout(R.layout.fragment_tab_container)
class WalletContainerFragment : BaseContainerFragment(), WalletContainerView {

  companion object {
    fun newInstance(tag: String?) = WalletContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }

  override val localNavigator: Navigator
    get() =
      Toothpick
        .openScope(AppScope.TAB_WALLET)
        .getInstance(Navigator::class.java)

  @InjectPresenter
  lateinit var presenter: WalletContainerPresenter

  @ProvidePresenter
  fun providePresenter(): WalletContainerPresenter =
    Toothpick
      .openScope(AppScope.TAB_WALLET)
      .getInstance(WalletContainerPresenter::class.java)

  override fun onAttach(context: Context?) {
    Toothpick
      .openScopes(AppScope.APPLICATION, AppScope.TAB_WALLET)
      .apply {
        installModules(WalletContainerModule(this@WalletContainerFragment))
        Toothpick.inject(this@WalletContainerFragment, this)
      }

    super.onAttach(context)
  }

  override fun onDestroyView() {
    Toothpick.closeScope(AppScope.TAB_WALLET)
    super.onDestroyView()
  }
}
