package com.sedsoftware.wexchanger.presentation.features.home

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseActivity
import toothpick.Toothpick

@LayoutResource(R.layout.activity_main)
class MainActivity : BaseActivity(), MainActivityView {

  @InjectPresenter
  lateinit var presenter: MainActivityPresenter

  @ProvidePresenter
  fun providePresenter(): MainActivityPresenter =
    Toothpick
      .openScope(AppScope.NETWORK)
      .getInstance(MainActivityPresenter::class.java)
}
