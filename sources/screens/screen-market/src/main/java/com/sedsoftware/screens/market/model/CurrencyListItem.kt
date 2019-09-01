package com.sedsoftware.screens.market.model

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.presentation.misc.DiffItem

data class CurrencyListItem(val currency: Currency, val isBase: Boolean, val isSelected: Boolean) : DiffItem {

    override fun getItemName(): String = currency.name

    override fun getItemHash(): Int = hashCode()
}
