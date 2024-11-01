package com.example.rocket.feature.rocketlist.presentation

import androidx.lifecycle.ViewModel
import com.example.rocket.feature.rocketlist.presentation.model.RocketListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RocketListViewModel: ViewModel() {
    private val _state = MutableStateFlow(RocketListScreenState.initState())
    internal val state: StateFlow<RocketListScreenState> = _state
}