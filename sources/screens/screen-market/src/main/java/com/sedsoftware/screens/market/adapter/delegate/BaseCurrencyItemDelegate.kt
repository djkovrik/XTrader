package com.sedsoftware.screens.market.adapter.delegate

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.screens.market.R
import com.sedsoftware.screens.market.adapter.CurrencyListAdapter.Listener
import com.sedsoftware.screens.market.model.CurrencyListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_add_pair_base.*

class BaseCurrencyItemDelegate(private val clickListener: Listener) : AdapterDelegate<List<CurrencyListItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): ItemViewHolder =
        ItemViewHolder(parent)

    override fun onBindViewHolder(items: List<CurrencyListItem>, position: Int, holder: ViewHolder, payloads: MutableList<Any>) {
        holder as ItemViewHolder
        holder.bind(items[position], clickListener)
    }

    override fun isForViewType(items: List<CurrencyListItem>, position: Int): Boolean =
        items[position].isBase

    class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_add_pair_base)), LayoutContainer {

        override val containerView: View? = itemView

        fun bind(item: CurrencyListItem, listener: Listener) {
            currencyTextView.text = String.format("%s", item.currency.name)
            itemContainer.setOnClickListener { listener.onItemClick(item) }
            checker.isVisible = item.isSelected
        }
    }
}
