package com.sedsoftware.screens.market

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyPairManager
import com.sedsoftware.core.presentation.base.BaseViewModel
import javax.inject.Inject

class MarketScreenViewModel @Inject constructor(
    private val managers: Map<Exchange, @JvmSuppressWildcards CurrencyPairManager>
) : BaseViewModel()
