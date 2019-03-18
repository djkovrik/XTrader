package com.sedsoftware.screens.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sedsoftware.core.domain.interactor.CurrencyPairLoader
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.screens.intro.model.ExchangeItem
import javax.inject.Inject

class IntroScreenViewModel @Inject constructor(
    private val downloaders: Set<@JvmSuppressWildcards CurrencyPairLoader>
) : BaseViewModel() {

    var exchanges: MutableLiveData<List<ExchangeItem>> = MutableLiveData()

}
