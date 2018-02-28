package com.sedsoftware.wexchanger.presentation.features.home

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.commons.extension.color
import com.sedsoftware.wexchanger.commons.extension.iconics
import com.sedsoftware.wexchanger.commons.extension.string
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.home_bottom_navigation
import toothpick.Toothpick

@LayoutResource(R.layout.activity_main)
class MainActivity : BaseActivity(), MainActivityView {

  @InjectPresenter
  lateinit var presenter: MainActivityPresenter

  @ProvidePresenter
  fun providePresenter(): MainActivityPresenter =
    Toothpick
      .openScope(AppScope.DATASOURCE)
      .getInstance(MainActivityPresenter::class.java)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initBottomNavigation()
  }

  private fun initBottomNavigation() {
    val marketTabItem =
        AHBottomNavigationItem(string(R.string.tab_market), iconics(CommunityMaterial.Icon.cmd_home))

    val ordersTabItem =
        AHBottomNavigationItem(string(R.string.tab_orders), iconics(CommunityMaterial.Icon.cmd_currency_btc))

    val walletTabItem =
        AHBottomNavigationItem(string(R.string.tab_wallet), iconics(CommunityMaterial.Icon.cmd_wallet))

    val trackerTabItem =
        AHBottomNavigationItem(string(R.string.tab_tracker), iconics(CommunityMaterial.Icon.cmd_radar))

    val settingsTabItem =
        AHBottomNavigationItem(string(R.string.tab_settings), iconics(CommunityMaterial.Icon.cmd_settings))

    home_bottom_navigation.addItems(
      listOf(marketTabItem, ordersTabItem, walletTabItem, trackerTabItem, settingsTabItem)
    )

    home_bottom_navigation.isBehaviorTranslationEnabled = true
    home_bottom_navigation.defaultBackgroundColor = color(R.color.colorPrimary)
    home_bottom_navigation.accentColor = color(R.color.colorTabIcon)
    home_bottom_navigation.inactiveColor = color(R.color.colorTabIconInactive)
    home_bottom_navigation.titleState = AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE
  }
}
