package com.sedsoftware.screens.intro.base.controller

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.screens.intro.base.store.IntroBaseStoreFactory
import com.sedsoftware.screens.intro.base.view.IntroBaseView
import kotlinx.coroutines.flow.map

// TODO ExperimentalCoroutinesApi !
class IntroBaseController {

    private val introBaseStore = IntroBaseStoreFactory(DefaultStoreFactory).create()

    fun onViewCreated(introBaseView: IntroBaseView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            introBaseStore.states.map { it.toViewModel() } bindTo introBaseView
        }

        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            introBaseView.events.map { it.toIntent() } bindTo introBaseStore
        }
    }
}
