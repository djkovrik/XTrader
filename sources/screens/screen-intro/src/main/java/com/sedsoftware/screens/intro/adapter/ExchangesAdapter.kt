package com.sedsoftware.screens.intro.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.screens.intro.R
import com.sedsoftware.screens.intro.model.ExchangeItem
import kotlinx.android.synthetic.main.fragment_intro_screen_item.view.intro_exchange_name
import javax.inject.Inject
import kotlin.properties.Delegates

class ExchangesAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var items: List<ExchangeItem> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ExchangeItemViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as ExchangeItemViewHolder
        holder.bindTo(items[position])
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemCount(): Int =
        items.size

    override fun getItemId(position: Int): Long =
        position.toLong()

    class ExchangeItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.fragment_intro_screen_item)) {

        fun bindTo(item: ExchangeItem) {
            itemView.intro_exchange_name.text = item.exchange.label
        }
    }
}
