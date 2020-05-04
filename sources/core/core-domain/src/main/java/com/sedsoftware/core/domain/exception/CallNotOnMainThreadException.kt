package com.sedsoftware.core.domain.exception

class CallNotOnMainThreadException(where: String) : Exception("$where called not on the main thread")
