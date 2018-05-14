package com.sedsoftware.xtrader.commons.extension

import android.support.v4.app.FragmentManager
import com.sedsoftware.xtrader.presentation.base.BaseNestedFragment

fun FragmentManager.disableAnimations() = setAnimationFlags(shouldSkip = true)

fun FragmentManager.enableAnimations() = setAnimationFlags(shouldSkip = false)

private fun FragmentManager.setAnimationFlags(shouldSkip: Boolean) =
  this.apply {
    fragments
      .flatMap { it.childFragmentManager.fragments }
      .forEach { (it as? BaseNestedFragment)?.skipAnimation = shouldSkip }
  }
