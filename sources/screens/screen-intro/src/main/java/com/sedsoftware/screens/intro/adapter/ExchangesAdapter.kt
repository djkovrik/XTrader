package com.sedsoftware.screens.intro.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.screens.intro.model.ExchangeItem

class ExchangesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<ExchangeItem> = mutableListOf()

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

    fun setData(exchanges: Set<Exchange>) {
        items.clear()
        items.addAll(exchanges.toList().map { ExchangeItem(it) })
        notifyDataSetChanged()
    }
}
