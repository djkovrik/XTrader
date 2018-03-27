package com.sedsoftware.wexchanger.presentation.features.main

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.commons.extension.colorFromAttr
import com.sedsoftware.wexchanger.commons.extension.disableAnimations
import com.sedsoftware.wexchanger.commons.extension.enableAnimations
import com.sedsoftware.wexchanger.commons.extension.iconics
import com.sedsoftware.wexchanger.commons.extension.string
import com.sedsoftware.wexchanger.commons.listener.BackButtonListener
import com.sedsoftware.wexchanger.commons.provider.ActionBarProvider
import com.sedsoftware.wexchanger.commons.provider.RouterProvider
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseActivity
import com.sedsoftware.wexchanger.presentation.features.main.containers.market.MarketContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.orders.OrdersContainerFragment
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import kotlinx.android.synthetic.main.activity_main.home_bottom_navigation
import kotlinx.android.synthetic.main.activity_main.toolbar
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage
import toothpick.Toothpick

@Layout(R.layout.activity_main)
class MainActivity : BaseActivity(), MainActivityView, ActionBarProvider, RouterProvider {

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

  private lateinit var marketContainer: MarketContainerFragment
  private lateinit var ordersContainer: OrdersContainerFragment
//  private lateinit var walletContainer: WalletContainerFragment
//  private lateinit var trackerContainer: TrackerContainerFragment

  private val navigator: Navigator = Navigator { commands: Array<out Command>? ->
    commands?.forEach { applyCommand(it) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    Toothpick.inject(this, Toothpick.openScope(AppScope.APPLICATION))

    super.onCreate(savedInstanceState)

    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener { onBackPressed() }

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

  override fun onBackPressed() {
    val fragment = supportFragmentManager.findFragmentById(R.id.home_tabs_container)

    when {
      fragment is BackButtonListener && fragment.onBackPressed() -> return
      else -> presenter.onBackPressed()
    }
  }

  override fun getCurrentActionBar(): ActionBar? = supportActionBar

  override fun getCurrentRouter(): Router = presenter.getMainRouter()

  private fun initContainers() {
    supportFragmentManager.let { manager ->

      marketContainer =
          manager.findFragmentByTag(MarketContainerFragment::class.simpleName) as MarketContainerFragment? ?:
          MarketContainerFragment.newInstance(MarketContainerFragment::class.simpleName).also { fragment ->
            manager.beginTransaction()
              .add(R.id.home_tabs_container, fragment, MarketContainerFragment::class.simpleName)
              .detach(fragment)
              .commitNow()
          }

      ordersContainer =
          manager.findFragmentByTag(OrdersContainerFragment::class.simpleName) as OrdersContainerFragment? ?:
          OrdersContainerFragment.newInstance(OrdersContainerFragment::class.simpleName).also { fragment ->
            manager.beginTransaction()
              .add(R.id.home_tabs_container, fragment, OrdersContainerFragment::class.simpleName)
              .detach(fragment)
              .commitNow()
          }

//<editor-fold desc="Temporary removed #1">
//      walletContainer =
//          manager.findFragmentByTag(WalletContainerFragment::class.simpleName) as WalletContainerFragment? ?:
//          WalletContainerFragment.newInstance(WalletContainerFragment::class.simpleName).also { fragment ->
//            manager.beginTransaction()
//              .add(R.id.home_tabs_container, fragment, WalletContainerFragment::class.simpleName)
//              .detach(fragment)
//              .commitNow()
//          }
//
//      trackerContainer =
//          manager.findFragmentByTag(TrackerContainerFragment::class.simpleName) as TrackerContainerFragment? ?:
//          TrackerContainerFragment.newInstance(TrackerContainerFragment::class.simpleName).also { fragment ->
//            manager.beginTransaction()
//              .add(R.id.home_tabs_container, fragment, TrackerContainerFragment::class.simpleName)
//              .detach(fragment)
//              .commitNow()
//          }
//</editor-fold>
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
    home_bottom_navigation.defaultBackgroundColor = colorFromAttr(R.attr.colorPrimary)
    home_bottom_navigation.accentColor = colorFromAttr(R.attr.colorTabIconActive)
    home_bottom_navigation.inactiveColor = colorFromAttr(R.attr.colorTabIconInactive)
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
        with(supportFragmentManager) {
          disableAnimations()

          when (command.screenKey) {
            AppScreen.MARKET_SCREEN -> {
              beginTransaction()
                .detach(ordersContainer)
                .attach(marketContainer)
                .commitNow()
            }
            AppScreen.ORDERS_SCREEN -> {
              beginTransaction()
                .detach(marketContainer)
                .attach(ordersContainer)
                .commitNow()
            }
//<editor-fold desc="Temporary removed #2">
//          AppScreen.WALLET_SCREEN -> {
//            fragmentManager.beginTransaction()
//              .detach(marketContainer)
//              .detach(ordersContainer)
//              .detach(trackerContainer)
//              .attach(walletContainer)
//              .commitNow()
//          }
//          AppScreen.TRACKER_SCREEN -> {
//            fragmentManager.beginTransaction()
//              .detach(marketContainer)
//              .detach(ordersContainer)
//              .detach(walletContainer)
//              .attach(trackerContainer)
//              .commitNow()
//          }
//          AppScreen.SETTINGS_SCREEN -> {
//            fragmentManager.beginTransaction()
//              .detach(marketContainer)
//              .detach(ordersContainer)
//              .detach(walletContainer)
//              .detach(trackerContainer)
//              .commitNow()
//          }
//</editor-fold>
          }
          enableAnimations()
        }
      }
    }
  }
}
