package com.sedsoftware.screens.market.view.adapter.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.screens.market.R
import com.sedsoftware.screens.market.view.adapter.CurrencyListAdapter.Listener
import com.sedsoftware.screens.market.store.model.CurrencyListItem

class MarketCurrencyItemDelegate(private val clickListener: Listener) : AdapterDelegate<List<CurrencyListItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): ItemViewHolder =
        ItemViewHolder(parent)

    override fun onBindViewHolder(items: List<CurrencyListItem>, position: Int, holder: ViewHolder, payloads: MutableList<Any>) {
        holder as ItemViewHolder
        val item = items[position]

//        if (payloads.isNotEmpty() && payloads[0] == CurrencyListAdapter.STATUS_PAYLOAD) {
//            holder.bindStatus(item)
//        } else {
//            holder.bindAll(item, clickListener)
//        }
    }

    override fun isForViewType(items: List<CurrencyListItem>, position: Int): Boolean =
        !items[position].isBase

    class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_add_pair_market)) {

//        fun bindAll(item: CurrencyListItem, listener: Listener) {
//            currencyTextView.text = String.format("%s", item.currency.name)
//            itemContainer.setOnClickListener { listener.onItemClick(item) }
//            checkedImageView.isVisible = item.isSelected
//            selector.isVisible = item.isSelected
//        }
//
//        fun bindStatus(item: CurrencyListItem) {
//            checkedImageView.isVisible = item.isSelected
//            selector.isVisible = item.isSelected
//        }
    }
}
