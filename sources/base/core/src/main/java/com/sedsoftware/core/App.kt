package com.sedsoftware.core

import android.content.Context
import com.sedsoftware.core.di.provider.ApplicationProvider

interface App {
  fun getApplicationContext(): Context
  fun getAppComponent(): ApplicationProvider
}
