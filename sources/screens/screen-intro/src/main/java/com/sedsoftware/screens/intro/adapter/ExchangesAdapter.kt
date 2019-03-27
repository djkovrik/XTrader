package com.sedsoftware.screens.intro.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.core.utils.enums.DownloadState
import com.sedsoftware.screens.intro.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_intro_screen_item.intro_exchange_name
import javax.inject.Inject
import kotlin.properties.Delegates

class ExchangesAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var items: Map<Exchange, DownloadState> by Delegates.observable(emptyMap()) { _, _, newValue ->

        exchanges.clear()
        states.clear()

        newValue.forEach { (exchange, state) ->
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            exchanges.add(exchange)
            states.add(state)
            states.add(state)
            states.add(state)
            states.add(state)
            states.add(state)
            states.add(state)
            states.add(state)
            states.add(state)
            states.add(state)
            states.add(state)
        }

        assert(exchanges.size == states.size) { "Exchanges and states size should be equal" }

        notifyDataSetChanged()
    }

    internal var clickListener: (Exchange) -> Unit = { _ -> }

    private val exchanges = mutableListOf<Exchange>()
    private val states = mutableListOf<DownloadState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ExchangeItemViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as ExchangeItemViewHolder
        holder.bind(exchanges[position], states[position], clickListener)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemCount(): Int =
        items.size + 9

    override fun getItemId(position: Int): Long =
        position.toLong()

    class ExchangeItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.fragment_intro_screen_item)), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(exchange: Exchange, downloadState: DownloadState, clickListener: (Exchange) -> Unit) {
            intro_exchange_name.text = exchange.label
            intro_exchange_name.setOnClickListener { clickListener(exchange) }
        }
    }
}
