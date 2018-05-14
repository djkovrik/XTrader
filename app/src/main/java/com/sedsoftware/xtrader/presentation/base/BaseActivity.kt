package com.sedsoftware.xtrader.presentation.base

import android.content.Context
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.mikepenz.iconics.context.IconicsContextWrapper
import com.sedsoftware.xtrader.commons.annotation.Layout
import com.sedsoftware.xtrader.commons.exception.MissingAnnotationException
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity() {

  @Inject
  lateinit var navigatorHolder: NavigatorHolder

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(IconicsContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val annotation = this::class.annotations.firstOrNull { it is Layout } as? Layout
    annotation?.value?.let { setContentView(it) }
        ?: throw MissingAnnotationException("$this must be annotated with specific Layout annotation.")
  }
}
