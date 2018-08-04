package com.sedsoftware.core.common.enum.currency

import com.sedsoftware.core.common.enum.Currency

enum class CurrencyReal(override val value: String) : Currency {
    EUR("Euro"),
    GBP("Pound Sterling"),
    JPY("Japanese Yen"),
    RUB("Russian Ruble"),
    USD("US Dollar"),
    USDT("Tether"),
}
