package com.sedsoftware.core.domain.exception

class CurrencyPairsLoadingError(exception: Throwable) : Exception("Failed to load currency pairs", exception)
