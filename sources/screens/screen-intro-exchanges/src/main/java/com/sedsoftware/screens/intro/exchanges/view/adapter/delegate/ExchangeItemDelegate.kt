package com.sedsoftware.screens.intro.exchanges.view.adapter.delegate

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.extension.dim
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.core.presentation.view.DownloadButton
import com.sedsoftware.screens.intro.exchanges.R
import com.sedsoftware.screens.intro.exchanges.store.model.DownloadState
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem
import com.sedsoftware.screens.intro.exchanges.view.adapter.ExchangeListAdapter.Listener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_intro_exchanges_item.*

class ExchangeItemDelegate(
    private val clickListener: Listener,
    private val assetsProvider: AssetsProvider
) : AdapterDelegate<List<ExchangeListItem>>() {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ItemViewHolder(parent)

    override fun onBindViewHolder(items: List<ExchangeListItem>, position: Int, holder: ViewHolder, payloads: MutableList<Any>) {
        holder as ItemViewHolder
        holder.bind(items[position], assetsProvider, clickListener)
        setAnimation(holder.itemView, holder.adapterPosition)
    }

    override fun isForViewType(items: List<ExchangeListItem>, position: Int): Boolean = true

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            AnimationUtils.loadAnimation(itemView.context, R.anim.recyclerview_item_appear)
                .apply { itemView.startAnimation(this) }
            lastPosition = position
        }
    }

    class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.fragment_intro_exchanges_item)), LayoutContainer {

        override val containerView: View? = itemView

        fun bind(item: ExchangeListItem, provider: AssetsProvider, listener: Listener) {
            with(item) {
                exchangeNameTextView.text = exchange.label
                exchangeLogoImageView.setImageResource(provider.getLogoResource(exchange))
                exchangeLogoImageView.dim(state != DownloadState.COMPLETED)
                exchangeDownloadButton.setState(mapDownloadState(state))

                exchangeDownloadButton.clickListener = { listener.onItemClick(this) }
            }
        }

        private fun mapDownloadState(state: DownloadState): DownloadButton.State =
            when (state) {
                DownloadState.AVAILABLE -> DownloadButton.State.AVAILABLE
                DownloadState.IN_PROGRESS -> DownloadButton.State.IN_PROGRESS
                DownloadState.COMPLETED -> DownloadButton.State.COMPLETED
                DownloadState.ERROR -> DownloadButton.State.ERROR
            }
    }
}
