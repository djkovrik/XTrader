package com.sedsoftware.screens.intro.adapter

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.extension.dim
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.core.presentation.misc.DiffItem
import com.sedsoftware.core.presentation.params.DownloadState
import com.sedsoftware.screens.intro.R
import com.sedsoftware.screens.intro.model.ExchangeListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_intro_screen_item.*

class ItemsAdapterDelegate(
        private val assetsProvider: AssetsProvider,
        private val clickListener: (ExchangeListItem) -> Unit
) : AbsListItemAdapterDelegate<ExchangeListItem, DiffItem, ItemsAdapterDelegate.ItemViewHolder>() {

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

        fun bind(item: ExchangeListItem, provider: AssetsProvider, listener: (ExchangeListItem) -> Unit) {
            with(item) {
                intro_exchange_name.text = exchange.label
                intro_exchange_logo.setImageResource(provider.getLogoResource(exchange))
                intro_exchange_logo.dim(state != DownloadState.COMPLETED)
                intro_button_download.setState(state)

                intro_button_download.clickListener = { listener(item) }
            }
        }
    }
}
