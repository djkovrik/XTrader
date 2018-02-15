package com.sedsoftware.domain.common

data class Result<out T>(
  val value: T,
  val error: Throwable = Throwable()
)
