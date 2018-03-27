package com.sedsoftware.wexchanger.presentation.base

import android.support.annotation.AnimRes
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.provider.ActionBarProvider

abstract class BaseNestedFragment : BaseFragment() {

  var skipAnimation = false

  override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation =
    when {
      skipAnimation -> applyAnimation(0)
      else -> applyAnimation(nextAnim)
    }

  override fun onResume() {
    super.onResume()

    parentFragment?.let { parent ->
      if (parent.childFragmentManager.backStackEntryCount > 0) {
        (parent.activity as? ActionBarProvider)?.getCurrentActionBar()?.setDisplayHomeAsUpEnabled(true)
      } else {
        (parent.activity as? ActionBarProvider)?.getCurrentActionBar()?.setDisplayHomeAsUpEnabled(false)
      }
    }
  }

  private fun applyAnimation(@AnimRes anim: Int): Animation =
    when {
      anim != 0 -> AnimationUtils.loadAnimation(context, anim)
      else -> AnimationUtils.loadAnimation(context, R.anim.none)
    }
}
