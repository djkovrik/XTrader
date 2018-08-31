package ${providerPackageName}

import android.util.Log
import ${corePackageName}.${providerModuleName}.Logger

class LoggerImpl : Logger {

    override fun d(message: String) {
        Log.d("Logger", message)
    }
}
