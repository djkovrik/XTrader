package com.sedsoftware.core.tools.impl

import android.os.Looper
import java.lang.RuntimeException

fun checkIfMainThread(where: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) throw RuntimeException("$where: initialized in Main thread")
}
