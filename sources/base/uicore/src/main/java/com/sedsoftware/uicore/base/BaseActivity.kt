package com.sedsoftware.uicore.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

  abstract fun inject()
  abstract fun getLayoutId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    inject()
    setContentView(getLayoutId())
  }
}
