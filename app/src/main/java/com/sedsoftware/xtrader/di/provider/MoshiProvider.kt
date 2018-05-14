package com.sedsoftware.xtrader.di.provider

import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Provider

class MoshiProvider @Inject constructor() : Provider<Moshi> {

  override fun get(): Moshi =
    Moshi.Builder().build()
}
