package com.sedsoftware.screens.tickers.ui.model

import com.sedsoftware.core.domain.entity.Currency

data class CurrencyListItem(val currency: Currency, val isBase: Boolean, val isSelected: Boolean)
