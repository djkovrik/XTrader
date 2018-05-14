package com.sedsoftware.xtrader.presentation.features.main.containers.tracker

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.sedsoftware.xtrader.R
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class TrackerContainerNavigator @Inject constructor(
  fragment: TrackerContainerFragment
) : SupportAppNavigator(fragment.activity, fragment.childFragmentManager, R.id.tab_container) {

  override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? =
    null

  override fun createFragment(screenKey: String?, data: Any?): Fragment? = null
}
