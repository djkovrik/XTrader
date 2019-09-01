package com.sedsoftware.screens.market.adapter.delegate

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.core.presentation.misc.DiffItem
import com.sedsoftware.screens.market.R
import com.sedsoftware.screens.market.adapter.CurrencyListAdapter.Listener
import com.sedsoftware.screens.market.model.CurrencyListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_add_pair_base.*

class BaseCurrencyItemDelegate(
    private val clickListener: Listener
) : AbsListItemAdapterDelegate<CurrencyListItem, DiffItem, BaseCurrencyItemDelegate.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ItemViewHolder =
        ItemViewHolder(parent)

    override fun onBindViewHolder(item: CurrencyListItem, holder: ItemViewHolder, payloads: MutableList<Any>) {
        holder.bind(item, clickListener)
    }

    override fun isForViewType(item: DiffItem, items: MutableList<DiffItem>, position: Int): Boolean =
        item is CurrencyListItem && item.isBase

    class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_add_pair_base)), LayoutContainer {

        override val containerView: View? = itemView

        fun bind(item: CurrencyListItem, listener: Listener) {
            currencyTextView.text = String.format("%s [%s]", item.currency.name, item.currency.label)
            itemContainer.setOnClickListener { listener.onItemClick(item) }
            checker.isVisible = item.isSelected
        }
    }
}
