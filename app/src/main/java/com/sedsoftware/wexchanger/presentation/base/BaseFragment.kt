package com.sedsoftware.wexchanger.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.commons.exception.MissingAnnotationException

abstract class BaseFragment : MvpAppCompatFragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val annotation = this::class.annotations.firstOrNull { it is LayoutResource } as? LayoutResource

    return annotation?.value?.let { inflater.inflate(it, container, false) }
        ?: throw MissingAnnotationException("$this must be annotated with specific LayoutResource annotation.")
  }
}
