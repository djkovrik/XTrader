package com.sedsoftware.core.domain.exception

class BuildOnMainThreadException(where: String) : Exception("$where : build called on the main thread")
