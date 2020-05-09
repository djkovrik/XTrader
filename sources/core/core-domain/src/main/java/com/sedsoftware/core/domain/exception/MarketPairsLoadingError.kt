package com.sedsoftware.core.domain.exception

class MarketPairsLoadingError(exception: Throwable) : Exception("Failed to market currency pairs", exception)
