package com.sedsoftware.domain.common

interface ResultCallback<in T> {

  fun onSuccess(value: T)

  fun onError(error: Throwable)
}
