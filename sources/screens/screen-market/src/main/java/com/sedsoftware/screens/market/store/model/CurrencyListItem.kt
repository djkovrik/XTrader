package com.sedsoftware.screens.market.store.model

import com.sedsoftware.core.domain.entity.Currency

data class CurrencyListItem(val currency: Currency, val isBase: Boolean, val isSelected: Boolean)
