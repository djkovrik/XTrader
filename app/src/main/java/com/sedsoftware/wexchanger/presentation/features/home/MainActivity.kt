package com.sedsoftware.wexchanger.presentation.features.home

import android.os.Bundle
import android.widget.Toast
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
import com.sedsoftware.wexchanger.presentation.features.home.containers.market.MarketContainerFragment
import com.sedsoftware.wexchanger.presentation.features.home.containers.orders.OrdersContainerFragment
import com.sedsoftware.wexchanger.presentation.features.home.containers.tracker.TrackerContainerFragment
import com.sedsoftware.wexchanger.presentation.features.home.containers.wallet.WalletContainerFragment
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import kotlinx.android.synthetic.main.activity_main.home_bottom_navigation
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage
import toothpick.Toothpick

@LayoutResource(R.layout.activity_main)
class MainActivity : BaseActivity(), MainActivityView {

  private companion object {
    const val BOTTOM_TAB_MARKET = 0
    const val BOTTOM_TAB_ORDERS = 1
    const val BOTTOM_TAB_WALLET = 2
    const val BOTTOM_TAB_TRACKER = 3
  }

  @InjectPresenter
  lateinit var presenter: MainActivityPresenter

  @ProvidePresenter
  fun providePresenter(): MainActivityPresenter =
    Toothpick
      .openScope(AppScope.APPLICATION)
      .getInstance(MainActivityPresenter::class.java)

  lateinit var marketContainer: MarketContainerFragment
  lateinit var ordersContainer: OrdersContainerFragment
  lateinit var walletContainer: WalletContainerFragment
  lateinit var trackerontainer: TrackerContainerFragment

  private val navigator: Navigator = Navigator { commands: Array<out Command>? ->
    commands?.forEach { applyCommand(it) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initContainers()
    initBottomNavigation()

    if (savedInstanceState == null) {
      home_bottom_navigation.currentItem = BOTTOM_TAB_MARKET
    }
  }

  override fun onPause() {
    super.onPause()
    navigatorHolder.removeNavigator()
  }

  override fun onResumeFragments() {
    super.onResumeFragments()
    navigatorHolder.setNavigator(navigator)
  }

  private fun initContainers() {
    supportFragmentManager.let { manager ->

      marketContainer =
          manager.findFragmentByTag(MarketContainerFragment::class.simpleName) as MarketContainerFragment? ?:
          MarketContainerFragment.newInstance(MarketContainerFragment::class.simpleName)

      if (!marketContainer.isAdded) {
        manager.beginTransaction()
          .add(R.id.home_tabs_container, marketContainer, MarketContainerFragment::class.simpleName)
          .detach(marketContainer)
          .commitNow()
      }

      ordersContainer =
          manager.findFragmentByTag(OrdersContainerFragment::class.simpleName) as OrdersContainerFragment? ?:
          OrdersContainerFragment.newInstance(OrdersContainerFragment::class.simpleName)

      if (!ordersContainer.isAdded) {
        manager.beginTransaction()
          .add(R.id.home_tabs_container, ordersContainer, OrdersContainerFragment::class.simpleName)
          .detach(ordersContainer)
          .commitNow()
      }

      walletContainer =
          manager.findFragmentByTag(WalletContainerFragment::class.simpleName) as WalletContainerFragment? ?:
          WalletContainerFragment.newInstance(WalletContainerFragment::class.simpleName)

      if (!walletContainer.isAdded) {
        manager.beginTransaction()
          .add(R.id.home_tabs_container, walletContainer, WalletContainerFragment::class.simpleName)
          .detach(walletContainer)
          .commitNow()
      }

      trackerontainer =
          manager.findFragmentByTag(TrackerContainerFragment::class.simpleName) as TrackerContainerFragment? ?:
          TrackerContainerFragment.newInstance(TrackerContainerFragment::class.simpleName)

      if (!trackerontainer.isAdded) {
        manager.beginTransaction()
          .add(R.id.home_tabs_container, trackerontainer, TrackerContainerFragment::class.simpleName)
          .detach(trackerontainer)
          .commitNow()
      }
    }
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

    home_bottom_navigation.setOnTabSelectedListener { position, _ ->
      when (position) {
        BOTTOM_TAB_MARKET -> presenter.onMarketTabSelected()
        BOTTOM_TAB_ORDERS -> presenter.onOrdersTabSelected()
        BOTTOM_TAB_WALLET -> presenter.onWalletTabSelected()
        BOTTOM_TAB_TRACKER -> presenter.onTrackerTabSelected()
        else -> presenter.onSettingsTabSelected()
      }
    }
  }

  private fun applyCommand(command: Command) {
    when (command) {
      is Back -> finish()
      is SystemMessage -> Toast.makeText(this@MainActivity, command.message, Toast.LENGTH_SHORT).show()
      is Forward -> {
        when (command.screenKey) {
          AppScreen.SETTINGS_SCREEN -> {
          }
        }
      }
      is Replace -> {
        val fragmentManager = supportFragmentManager

        when (command.screenKey) {
          AppScreen.MARKET_SCREEN -> {
            fragmentManager.beginTransaction()
              .detach(ordersContainer)
              .detach(walletContainer)
              .detach(trackerontainer)
              .attach(marketContainer)
              .commitNow()
          }
          AppScreen.ORDERS_SCREEN -> {
            fragmentManager.beginTransaction()
              .detach(marketContainer)
              .detach(walletContainer)
              .detach(trackerontainer)
              .attach(ordersContainer)
              .commitNow()
          }
          AppScreen.WALLET_SCREEN -> {
            fragmentManager.beginTransaction()
              .detach(marketContainer)
              .detach(ordersContainer)
              .detach(trackerontainer)
              .attach(walletContainer)
              .commitNow()
          }
          AppScreen.TRACKER_SCREEN -> {
            fragmentManager.beginTransaction()
              .detach(marketContainer)
              .detach(ordersContainer)
              .detach(walletContainer)
              .attach(trackerontainer)
              .commitNow()
          }
          AppScreen.SETTINGS_SCREEN -> {
            fragmentManager.beginTransaction()
              .detach(marketContainer)
              .detach(ordersContainer)
              .detach(walletContainer)
              .detach(trackerontainer)
              .commitNow()
          }
        }
      }
    }
  }
}
