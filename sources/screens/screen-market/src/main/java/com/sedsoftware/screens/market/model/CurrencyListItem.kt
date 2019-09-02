package com.sedsoftware.screens.market.model

import com.sedsoftware.core.domain.entity.Currency

data class CurrencyListItem(val currency: Currency, val isBase: Boolean, var isSelected: Boolean)
