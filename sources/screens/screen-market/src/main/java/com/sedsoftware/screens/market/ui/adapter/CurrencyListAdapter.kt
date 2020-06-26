package com.sedsoftware.screens.market.ui.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sedsoftware.screens.market.ui.adapter.delegate.baseCurrencyItemDelegate
import com.sedsoftware.screens.market.ui.adapter.delegate.marketCurrencyItemDelegate
import com.sedsoftware.screens.market.ui.model.CurrencyListItem

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
