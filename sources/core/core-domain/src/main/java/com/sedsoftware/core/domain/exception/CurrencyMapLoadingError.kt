package com.sedsoftware.core.domain.exception

class CurrencyMapLoadingError(exception: Throwable) : Exception("Failed to load currency map", exception)
