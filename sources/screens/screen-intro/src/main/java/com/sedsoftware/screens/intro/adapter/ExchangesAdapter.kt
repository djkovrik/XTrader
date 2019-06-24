package com.sedsoftware.screens.intro.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.misc.DiffItem
import com.sedsoftware.screens.intro.model.ExchangeListItem
import javax.inject.Inject

class ExchangesAdapter @Inject constructor(
        assetsProvider: AssetsProvider
) : AsyncListDifferDelegationAdapter<DiffItem>(DiffItem.DIFF_CALLBACK) {

    internal var clickListener: (ExchangeListItem) -> Unit = { _ -> }

    init {
        delegatesManager.addDelegate(ItemsAdapterDelegate(assetsProvider, clickListener))
    }
}
