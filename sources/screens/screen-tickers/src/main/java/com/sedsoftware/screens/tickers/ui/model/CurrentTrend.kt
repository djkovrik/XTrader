package com.sedsoftware.screens.tickers.ui.model

import androidx.annotation.ColorRes
import com.sedsoftware.screens.tickers.R

enum class CurrentTrend(@ColorRes val color: Int) {
    UP(R.color.colorPercentPositive),
    DOWN(R.color.colorPercentNegative),
    STILL(R.color.colorPercentStill),
    NONE(0)
}
