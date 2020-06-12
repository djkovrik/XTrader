package com.sedsoftware.core.tools.impl.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.sedsoftware.core.domain.tools.NetworkHandler
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Suppress("DEPRECATION")
@Reusable
class AppNetworkHandler @Inject constructor(
    @ApplicationContext val context: Context
) : NetworkHandler {

    override val isConnected: Boolean
        get() {
            var result = false
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm?.run {
                    cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                        if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            result = true
                        } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            result = true
                        }
                    }
                }
            } else {
                cm?.run {
                    cm.activeNetworkInfo?.run {
                        if (type == ConnectivityManager.TYPE_WIFI) {
                            result = true
                        } else if (type == ConnectivityManager.TYPE_MOBILE) {
                            result = true
                        }
                    }
                }
            }
            return result
        }
}
