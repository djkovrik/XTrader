package com.sedsoftware.wexchanger.presentation.features.main.containers.wallet

import androidx.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.WalletContainerModule
import toothpick.Toothpick

@Layout(R.layout.fragment_tab_container)
class WalletContainerFragment : BaseContainerFragment(), WalletContainerView {

  companion object {
    fun newInstance(tag: String?) = WalletContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }

  @InjectPresenter
  lateinit var presenter: WalletContainerPresenter

  @ProvidePresenter
  fun providePresenter(): WalletContainerPresenter {
    val scope = Toothpick.openScopes(AppScope.APPLICATION, AppScope.TAB_WALLET)
    scope.installModules(WalletContainerModule(this))
    Toothpick.inject(this, scope)
    return scope.getInstance(WalletContainerPresenter::class.java)
  }
}
