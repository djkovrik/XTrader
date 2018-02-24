package com.sedsoftware.wexchanger.presentation.features.home

import com.arellomobile.mvp.InjectViewState
import com.sedsoftware.domain.common.ResultCallback
import com.sedsoftware.domain.entity.CurrencyPair
import com.sedsoftware.domain.interactor.GetCurrencyPairsUseCase
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
      .getPairs(object : ResultCallback<List<CurrencyPair>> {
        override fun onSuccess(value: List<CurrencyPair>) {
          Timber.d("Pairs: $value")
        }

        override fun onError(error: Throwable) {
          Timber.e("Error: ${error.message}")
        }
      })
  }
}
