package com.sedsoftware.screens.market.view.adapter.delegate

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sedsoftware.screens.market.databinding.ItemAddPairBaseBinding
import com.sedsoftware.screens.market.view.adapter.CurrencyListAdapter.Listener
import com.sedsoftware.screens.market.view.model.CurrencyListItem

fun baseCurrencyItemDelegate(clickListener: Listener) =
    adapterDelegateViewBinding<CurrencyListItem, CurrencyListItem, ItemAddPairBaseBinding>(
        { layoutInflater, root -> ItemAddPairBaseBinding.inflate(layoutInflater, root, false) },
        { item, _, _ -> item.isBase }
    ) {

        binding.itemContainer.setOnClickListener {
            clickListener.onItemClick(item)
        }

        bind {
            with(item) {
                binding.currencyTextView.text = String.format("%s", item.currency.name)
                binding.checkedImageView.isVisible = item.isSelected
                binding.selector.isVisible = item.isSelected
            }
        }
    }
