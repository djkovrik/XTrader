package com.sedsoftware.screens.intro.exchanges.view.adapter.delegate

import android.view.View
import android.view.animation.AnimationUtils
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.presentation.extension.dim
import com.sedsoftware.core.presentation.view.DownloadButton
import com.sedsoftware.screens.intro.exchanges.R
import com.sedsoftware.screens.intro.exchanges.databinding.FragmentIntroExchangesItemBinding
import com.sedsoftware.screens.intro.exchanges.store.model.DownloadState
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem
import com.sedsoftware.screens.intro.exchanges.view.adapter.ExchangeListAdapter
import com.sedsoftware.screens.intro.exchanges.view.adapter.ExchangeListAdapter.Listener

fun exchangeItemDelegate(listener: Listener, provider: AssetsProvider) =
    adapterDelegateViewBinding<ExchangeListItem, ExchangeListItem, FragmentIntroExchangesItemBinding>(
        { layoutInflater, root -> FragmentIntroExchangesItemBinding.inflate(layoutInflater, root, false) }
    ) {

        binding.exchangeDownloadButton.clickListener = {
            listener.onItemClick(item)
        }

        bind {
            with(item) {
                binding.exchangeNameTextView.text = exchange.label
                binding.exchangeLogoImageView.setImageResource(provider.getLogoResource(exchange))
                binding.exchangeLogoImageView.dim(state != DownloadState.COMPLETED)
                binding.exchangeDownloadButton.setState(mapDownloadState(state))
            }

            setAnimation(this.itemView, this.adapterPosition)
        }
    }

private fun setAnimation(itemView: View, position: Int) {
    if (position > ExchangeListAdapter.lastItemPosition) {
        AnimationUtils.loadAnimation(itemView.context, R.anim.recyclerview_item_appear)
            .apply { itemView.startAnimation(this) }
        ExchangeListAdapter.lastItemPosition = position
    }
}

private fun mapDownloadState(state: DownloadState): DownloadButton.State =
    when (state) {
        DownloadState.AVAILABLE -> DownloadButton.State.AVAILABLE
        DownloadState.IN_PROGRESS -> DownloadButton.State.IN_PROGRESS
        DownloadState.COMPLETED -> DownloadButton.State.COMPLETED
        DownloadState.ERROR -> DownloadButton.State.ERROR
    }

