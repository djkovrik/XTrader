package ${providerPackageName}

import android.content.SharedPreferences
import android.content.res.Resources
import ${corePackageName}.${providerModuleName}.Settings
import javax.inject.Inject

class SettingsImpl @Inject constructor(
    private val resources: Resources,
    private val preferences: SharedPreferences
) : Settings {

    override fun saveValue(value: String) {

    }
}
