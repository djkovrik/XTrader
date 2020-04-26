package com.sedsoftware.core.domain.errorhandler

interface ErrorHandler {
    fun consume(error: Throwable)
    fun attachView(view: CanShowError)
    fun detachView()
}
