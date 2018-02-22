package com.sedsoftware.wexchanger.presentation.base

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatActivity
import com.mikepenz.iconics.context.IconicsContextWrapper

abstract class BaseActivity : MvpAppCompatActivity() {

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(IconicsContextWrapper.wrap(newBase))
  }
}
