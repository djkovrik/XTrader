package com.sedsoftware.screens.intro.exchanges.view.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem
import com.sedsoftware.screens.intro.exchanges.view.adapter.delegate.exchangeItemDelegate

class ExchangeListAdapter(
    clickListener: Listener,
    assetsProvider: AssetsProvider
) : ListDelegationAdapter<List<ExchangeListItem>>() {

    interface Listener {
        fun onItemClick(item: ExchangeListItem)
    }

    companion object {
        var lastItemPosition = -1
    }

    init {
        items = ArrayList()

        delegatesManager.addDelegate(exchangeItemDelegate(clickListener, assetsProvider))
    }
}
