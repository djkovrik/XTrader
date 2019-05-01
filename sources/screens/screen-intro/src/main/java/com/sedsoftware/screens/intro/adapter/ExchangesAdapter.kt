package com.sedsoftware.screens.intro.adapter

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.params.DownloadState
import com.sedsoftware.core.presentation.extension.dim
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.screens.intro.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_intro_screen_item.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ExchangesAdapter @Inject constructor(
    private val assetsProvider: AssetsProvider
) : RecyclerView.Adapter<ViewHolder>() {

    internal var items: Map<Exchange, DownloadState> by Delegates.observable(emptyMap()) { _, _, newValue ->

        exchanges.clear()
        states.clear()

        newValue.forEach { (exchange, state) ->
            exchanges.add(exchange)
            states.add(state)
        }

        assert(exchanges.size == states.size) { "Exchanges and states size should be equal" }

        notifyDataSetChanged()
    }

    internal var clickListener: (Exchange) -> Unit = { _ -> }

    private val exchanges = mutableListOf<Exchange>()
    private val states = mutableListOf<DownloadState>()

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ExchangeItemViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as ExchangeItemViewHolder
        holder.bind(exchanges[position], states[position], assetsProvider, clickListener)
        setAnimation(holder.itemView, position)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = position.toLong()

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            AnimationUtils.loadAnimation(itemView.context, R.anim.recyclerview_item_appear)
                .apply { itemView.startAnimation(this) }
            lastPosition = position
        }
    }

    class ExchangeItemViewHolder(parent: ViewGroup) :
        ViewHolder(parent.inflate(R.layout.fragment_intro_screen_item)), LayoutContainer {

        override val containerView: View? = itemView

        fun bind(exchange: Exchange, state: DownloadState, provider: AssetsProvider, listener: (Exchange) -> Unit) {
            intro_exchange_name.text = exchange.label
            intro_exchange_logo.setImageResource(provider.getLogoResource(exchange))
            intro_exchange_logo.dim(state != DownloadState.COMPLETED)
            intro_button_download.setState(state)

            intro_button_download.clickListener = { listener(exchange) }
        }
    }
}
