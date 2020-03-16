package com.sedsoftware.core.utils.exception

class CurrencyMapLoadingError(exception: Exception) : Exception("Failed to load currency map", exception)
