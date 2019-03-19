package com.sedsoftware.screens.intro.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.core.utils.enums.DownloadState
import com.sedsoftware.screens.intro.R
import kotlinx.android.synthetic.main.fragment_intro_screen_item.view.intro_exchange_name
import javax.inject.Inject
import kotlin.properties.Delegates

class ExchangesAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var items: Map<Exchange, DownloadState> by Delegates.observable(emptyMap()) { _, _, newValue ->

        exchanges.clear()
        states.clear()

        newValue.forEach { (exchange, state) ->
            exchanges.add(exchange)
            states.add(state)
        }

        assert(exchanges.size == states.size) { " Exchanges and states size should be equal" }

        notifyDataSetChanged()
    }

    private val exchanges = mutableListOf<Exchange>()
    private val states = mutableListOf<DownloadState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ExchangeItemViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as ExchangeItemViewHolder
        holder.bindTo(exchanges[position], states[position])
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

        fun bindTo(exchange: Exchange, downloadState: DownloadState) {
            itemView.intro_exchange_name.text = exchange.label
        }
    }
}
