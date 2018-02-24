package com.sedsoftware.wexchanger.presentation.features.home

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.domain.interactor.GetCurrencyPairsUseCase
import com.sedsoftware.wexchanger.commons.extension.addToCancelableJobsPool
import com.sedsoftware.wexchanger.presentation.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject constructor(
  private val getCurrencyPairsUseCase: GetCurrencyPairsUseCase
) : BasePresenter<MainActivityView>() {

  override fun attachView(view: MainActivityView?) {
    super.attachView(view)

    getCurrencyPairsUseCase
      .getPairs({ list ->
        Timber.d("Pairs: $list") }, {
          throwable ->
        Timber.e("Error: ${throwable.message}")
      })
      .addToCancelableJobsPool(jobs)
  }
}
