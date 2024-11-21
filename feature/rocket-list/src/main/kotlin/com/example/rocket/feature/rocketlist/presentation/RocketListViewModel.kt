package com.example.rocket.feature.rocketlist.presentation

import com.example.rocket.core.architecture.presentation.BaseViewModel
import com.example.rocket.feature.rocketlist.presentation.model.RocketListAction
import com.example.rocket.feature.rocketlist.presentation.model.RocketListNavigationEvent
import com.example.rocket.feature.rocketlist.presentation.model.RocketListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RocketListViewModel : BaseViewModel<RocketListScreenState, RocketListAction, RocketListNavigationEvent, Unit>() {
    private val _state = MutableStateFlow(RocketListScreenState.initState())
    override val state: StateFlow<RocketListScreenState> = _state

    override fun send(action: RocketListAction) {
        when (action) {
            is RocketListAction.OpenDetail -> {
                onOpenDetail(id = action.id, name = action.name)
            }
        }
    }

    private fun onOpenDetail(id: String, name: String) {
        navigateTo(RocketListNavigationEvent.ToDetail(id, name))
    }
}
