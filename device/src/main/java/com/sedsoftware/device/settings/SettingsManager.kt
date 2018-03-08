package com.sedsoftware.device.settings

import android.content.SharedPreferences
import android.content.res.Resources
import android.support.annotation.StringRes
import com.sedsoftware.device.R
import com.sedsoftware.domain.device.Settings
import javax.inject.Inject

class SettingsManager @Inject constructor(
  private val resources: Resources,
  private val preferences: SharedPreferences
) : Settings {

  override fun getPairsLastSyncTimestamp(): Long =
    getLongPref(R.string.pref_key_pairs_last_sync_timestamp, 0)

  override fun setPairsLastSyncTimestamp(timestamp: Long) =
    setLongPref(R.string.pref_key_pairs_last_sync_timestamp, timestamp)

  private fun getLongPref(@StringRes key: Int, default: Long): Long =
    preferences.getLong(resources.getString(key), default)

  private fun setLongPref(@StringRes key: Int, value: Long) {
    preferences.edit().putLong(resources.getString(key), value).apply()
  }
}
