package com.sedsoftware.core.utils.exception

class BuildOnMainThreadException(where: String) : Exception("$where : build called on the main thread")
