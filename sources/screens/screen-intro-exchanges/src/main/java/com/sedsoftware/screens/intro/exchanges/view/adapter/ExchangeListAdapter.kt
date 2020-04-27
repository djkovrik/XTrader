package com.sedsoftware.screens.intro.exchanges.view.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.screens.intro.exchanges.view.adapter.delegate.ExchangeItemDelegate
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem
import javax.inject.Inject

class ExchangeListAdapter @Inject constructor(
    clickListener: Listener,
    assetsProvider: AssetsProvider
) : ListDelegationAdapter<List<ExchangeListItem>>() {

    interface Listener {
        fun onItemClick(item: ExchangeListItem)
    }

    init {
        items = ArrayList()

        delegatesManager.addDelegate(
            ExchangeItemDelegate(
                assetsProvider,
                clickListener
            )
        )
    }
}
