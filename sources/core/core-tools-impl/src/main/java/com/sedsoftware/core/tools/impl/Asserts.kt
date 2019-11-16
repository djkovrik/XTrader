package com.sedsoftware.core.tools.impl

import android.os.Looper
import com.sedsoftware.core.utils.exception.BuildOnMainThreadException
import java.lang.RuntimeException

fun checkIfMainThread(where: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) throw BuildOnMainThreadException(where)
}
