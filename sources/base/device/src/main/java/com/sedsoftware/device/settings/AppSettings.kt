package com.sedsoftware.device.settings

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.StringRes
import com.sedsoftware.coreapi.device.Settings
import javax.inject.Inject

class AppSettings @Inject constructor(
    private val resources: Resources,
    private val preferences: SharedPreferences
) : Settings {

    private companion object {
        inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
            val editor = this.edit()
            operation(editor)
            editor.apply()
        }
    }


    private fun SharedPreferences.setValue(key: String, value: Any) {
        when (value) {
            is String -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not implemented yet")
        }
    }

    private inline fun <reified T : Any> SharedPreferences.getValue(key: String, defaultValue: T? = null): T =
        when (T::class) {
            String::class -> getString(key, defaultValue as? String ?: "") as T
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
            Long::class -> getLong(key, defaultValue as? Long ?: -1L) as T
            else -> throw UnsupportedOperationException("Not implemented yet")
        }

    private fun AppSettings.key(@StringRes resId: Int): String = resources.getString(resId)
}
