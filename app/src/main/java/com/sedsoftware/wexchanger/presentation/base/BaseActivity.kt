package com.sedsoftware.wexchanger.presentation.base

import android.content.Context
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.mikepenz.iconics.context.IconicsContextWrapper
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.commons.exception.MissingAnnotationException
import com.sedsoftware.wexchanger.di.AppScope
import ru.terrakok.cicerone.NavigatorHolder
import toothpick.Toothpick
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

    Toothpick.inject(this, Toothpick.openScope(AppScope.APPLICATION))
  }
}
