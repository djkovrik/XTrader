package com.sedsoftware.screens.market.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sedsoftware.screens.market.adapter.delegate.BaseCurrencyItemDelegate
import com.sedsoftware.screens.market.adapter.delegate.MarketCurrencyItemDelegate
import com.sedsoftware.screens.market.model.CurrencyListItem

class CurrencyListAdapter(
    clickListener: Listener
) : ListDelegationAdapter<List<CurrencyListItem>>() {

    interface Listener {
        fun onItemClick(item: CurrencyListItem)
    }

    companion object {
        const val STATUS_PAYLOAD = 1
    }

    init {
        delegatesManager
            .addDelegate(BaseCurrencyItemDelegate(clickListener))
            .addDelegate(MarketCurrencyItemDelegate(clickListener))
    }
}
