package com.sedsoftware.screens.market.view.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sedsoftware.screens.market.view.adapter.delegate.baseCurrencyItemDelegate
import com.sedsoftware.screens.market.view.adapter.delegate.marketCurrencyItemDelegate
import com.sedsoftware.screens.market.view.model.CurrencyListItem

class CurrencyListAdapter(
    clickListener: Listener
) : ListDelegationAdapter<List<CurrencyListItem>>() {

    interface Listener {
        fun onItemClick(item: CurrencyListItem)
    }

    init {
        delegatesManager
            .addDelegate(baseCurrencyItemDelegate(clickListener))
            .addDelegate(marketCurrencyItemDelegate(clickListener))
    }
}
