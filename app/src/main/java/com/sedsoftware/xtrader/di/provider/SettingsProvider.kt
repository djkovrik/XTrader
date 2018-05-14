package com.sedsoftware.xtrader.di.provider

import android.content.SharedPreferences
import android.content.res.Resources
import com.sedsoftware.device.settings.SettingsManager
import javax.inject.Inject
import javax.inject.Provider

class SettingsProvider @Inject constructor(
  private val resources: Resources,
  private val preferences: SharedPreferences
) : Provider<SettingsManager> {

  override fun get(): SettingsManager =
    SettingsManager(resources, preferences)
}
