package com.sedsoftware.wexchanger.presentation.base

import android.content.Context
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.mikepenz.iconics.context.IconicsContextWrapper
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.commons.exception.MissingAnnotationException

abstract class BaseActivity : MvpAppCompatActivity() {

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(IconicsContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val annotation = this::class.annotations.firstOrNull { it is LayoutResource } as? LayoutResource
    annotation?.value?.let { setContentView(it) }
        ?: throw MissingAnnotationException("$this must be annotated with specific LayoutResource annotation.")
  }
}
