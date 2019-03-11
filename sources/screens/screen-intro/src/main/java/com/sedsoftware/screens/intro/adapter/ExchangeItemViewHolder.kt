package com.sedsoftware.screens.intro.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedsoftware.core.presentation.extension.inflate
import com.sedsoftware.screens.intro.R
import com.sedsoftware.screens.intro.model.ExchangeItem
import kotlinx.android.synthetic.main.fragment_intro_screen_item.view.intro_exchange_name

class ExchangeItemViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.fragment_intro_screen_item)) {

    fun bindTo(item: ExchangeItem) {
        itemView.intro_exchange_name.text = item.exchange.label
    }
}
