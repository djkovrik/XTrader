package com.sedsoftware.xtrader.presentation.features.main.di.provider

import com.sedsoftware.xtrader.presentation.features.main.containers.tracker.TrackerContainerFragment
import com.sedsoftware.xtrader.presentation.features.main.containers.tracker.TrackerContainerNavigator
import javax.inject.Provider

class TrackerNavigatorProvider(private val fragment: TrackerContainerFragment) :
  Provider<TrackerContainerNavigator> {

  override fun get(): TrackerContainerNavigator = TrackerContainerNavigator(fragment)
}
