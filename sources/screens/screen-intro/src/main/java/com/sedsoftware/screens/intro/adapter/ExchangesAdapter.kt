package com.sedsoftware.screens.intro.adapter

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.extension.dim
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.core.presentation.params.DownloadState
import com.sedsoftware.screens.intro.R
import com.sedsoftware.screens.intro.model.ExchangeListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_intro_screen_item.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ExchangesAdapter @Inject constructor(
    private val assetsProvider: AssetsProvider
) : RecyclerView.Adapter<ViewHolder>() {

    internal var items: List<ExchangeListItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (ExchangeListItem) -> Unit = { _ -> }

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ExchangeItemViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as ExchangeItemViewHolder
        holder.bind(items[position], assetsProvider, clickListener)
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
