package com.sedsoftware.screens.intro.adapter.delegate

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.extension.dim
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.core.presentation.misc.DiffItem
import com.sedsoftware.core.presentation.type.DownloadState
import com.sedsoftware.screens.intro.R
import com.sedsoftware.screens.intro.adapter.ExchangeListAdapter.Listener
import com.sedsoftware.screens.intro.model.ExchangeListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_intro_screen_item.*

class ExchangeItemDelegate(
    private val assetsProvider: AssetsProvider,
    private val clickListener: Listener
) : AbsListItemAdapterDelegate<ExchangeListItem, DiffItem, ExchangeItemDelegate.ItemViewHolder>() {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup): ItemViewHolder =
        ItemViewHolder(parent)

    override fun onBindViewHolder(item: ExchangeListItem, holder: ItemViewHolder, payloads: MutableList<Any>) {
        holder.bind(item, assetsProvider, clickListener)
        setAnimation(holder.itemView, holder.adapterPosition)
    }

    override fun isForViewType(item: DiffItem, items: MutableList<DiffItem>, position: Int): Boolean =
        item is ExchangeListItem

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            AnimationUtils.loadAnimation(itemView.context, R.anim.recyclerview_item_appear)
                .apply { itemView.startAnimation(this) }
            lastPosition = position
        }
    }

    class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.fragment_intro_screen_item)), LayoutContainer {

        override val containerView: View? = itemView

        fun bind(item: ExchangeListItem, provider: AssetsProvider, listener: Listener) {
            with(item) {
                exchangeNameTextView.text = exchange.label
                exchangeLogoImageView.setImageResource(provider.getLogoResource(exchange))
                exchangeLogoImageView.dim(state != DownloadState.COMPLETED)
                exchangeDownloadButton.setState(state)

                exchangeDownloadButton.clickListener = { listener.onItemClick(this) }
            }
        }
    }
}
