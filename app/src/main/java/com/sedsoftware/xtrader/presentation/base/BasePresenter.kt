package com.sedsoftware.xtrader.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.experimental.Job

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {

  protected val jobs: MutableList<Job> = ArrayList()

  override fun detachView(view: View) {
    super.detachView(view)

    jobs.forEach { if (it.isActive) it.cancel() }
    jobs.clear()
  }
}
