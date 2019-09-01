package com.sedsoftware.screens.intro.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.misc.DiffItem
import com.sedsoftware.screens.intro.adapter.delegate.ExchangeItemDelegate
import com.sedsoftware.screens.intro.model.ExchangeListItem
import javax.inject.Inject

class ExchangeListAdapter @Inject constructor(
    clickListener: Listener,
    assetsProvider: AssetsProvider
) : AsyncListDifferDelegationAdapter<DiffItem>(DiffItem.DIFF_CALLBACK) {

    interface Listener {
        fun onItemClick(item: ExchangeListItem)
    }

    init {
        delegatesManager.addDelegate(
            ExchangeItemDelegate(
                assetsProvider,
                clickListener
            )
        )
    }
}
