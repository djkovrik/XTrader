package com.sedsoftware.wexchanger.presentation.features.main.di.provider

import com.sedsoftware.wexchanger.presentation.features.main.containers.tracker.TrackerContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.tracker.TrackerContainerNavigator
import javax.inject.Provider

class TrackerNavigatorProvider(private val fragment: TrackerContainerFragment) :
  Provider<TrackerContainerNavigator> {

  override fun get(): TrackerContainerNavigator = TrackerContainerNavigator(fragment)
}
