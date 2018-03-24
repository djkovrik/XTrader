package com.sedsoftware.wexchanger.presentation.base

import android.support.annotation.AnimRes
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.sedsoftware.wexchanger.R

abstract class BaseNestedFragment : BaseFragment() {

  override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation =
    AnimationUtils.loadAnimation(context, R.anim.none)
//    when {
//      skipAnimation -> AnimationUtils.loadAnimation(context, R.anim.none)
//      else -> applyAnimation(nextAnim)
//    }

  private fun applyAnimation(@AnimRes anim: Int): Animation =
    if (anim != 0)
      AnimationUtils.loadAnimation(context, anim)
    else
      AnimationUtils.loadAnimation(context, R.anim.none)
}
