package com.sedsoftware.core.tools.impl

import android.os.Looper
import com.sedsoftware.core.domain.exception.CallOnMainThreadException
import com.sedsoftware.core.domain.exception.CallNotOnMainThreadException

fun Any.checkIsNotMainThread(where: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) throw CallOnMainThreadException(where)
}

fun Any.checkIsMainThread(where: String) {
    if (Looper.myLooper() != Looper.getMainLooper()) throw CallNotOnMainThreadException(where)
}
