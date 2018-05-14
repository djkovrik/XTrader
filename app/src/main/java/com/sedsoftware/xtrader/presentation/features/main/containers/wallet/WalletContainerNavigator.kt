package com.sedsoftware.xtrader.presentation.features.main.containers.wallet

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.sedsoftware.xtrader.R
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class WalletContainerNavigator @Inject constructor(
  fragment: WalletContainerFragment
) : SupportAppNavigator(fragment.activity, fragment.childFragmentManager, R.id.tab_container) {

  override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? =
    null

  override fun createFragment(screenKey: String?, data: Any?): Fragment? = null
}
