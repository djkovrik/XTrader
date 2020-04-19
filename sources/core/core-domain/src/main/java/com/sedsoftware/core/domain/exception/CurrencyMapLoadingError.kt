package com.sedsoftware.core.domain.exception

class CurrencyMapLoadingError(exception: Exception) : Exception("Failed to load currency map", exception)
