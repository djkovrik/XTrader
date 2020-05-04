package com.sedsoftware.core.domain.exception

class CallOnMainThreadException(where: String) : Exception("$where called on the main thread")
