package com.sedsoftware.core.domain.exception

class TickerPairsLoadingError(exception: Throwable) : Exception("Failed to ticker pairs", exception)
