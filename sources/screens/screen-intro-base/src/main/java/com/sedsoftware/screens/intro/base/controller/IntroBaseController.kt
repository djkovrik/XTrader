package com.sedsoftware.screens.intro.base.controller

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.view.IntroBaseView
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// TODO ExperimentalCoroutinesApi !
class IntroBaseController @Inject constructor(
    private val introBaseStore: IntroBaseStore
) {

    fun onViewCreated(introBaseView: IntroBaseView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            introBaseStore.states.map { it.toViewModel() } bindTo introBaseView
        }

        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            introBaseView.events.map { it.toIntent() } bindTo introBaseStore
        }
    }
}
