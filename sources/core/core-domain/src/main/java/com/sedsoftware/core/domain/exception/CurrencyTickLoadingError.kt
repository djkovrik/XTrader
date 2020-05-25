package com.sedsoftware.core.domain.exception

class CurrencyTickLoadingError(val symbol: String, exception: Throwable) :
    Exception("Failed to tick data for $symbol:", exception)
