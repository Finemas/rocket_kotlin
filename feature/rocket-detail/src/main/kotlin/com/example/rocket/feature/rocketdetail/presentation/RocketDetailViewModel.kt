package com.example.rocket.feature.rocketdetail.presentation

import android.util.Log
import com.example.rocket.core.architecture.presentation.BaseViewModel
import com.example.rocket.feature.rocketdetail.presentation.model.RocketDetailScreenState
import com.example.rocket.library.rocketdetail.domain.model.RocketDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RocketDetailViewModel internal constructor(
    val id: String,
    val name: String,
    rocketDetail: RocketDetail?,
) : BaseViewModel<RocketDetailScreenState, Unit, Unit, Unit>() {
    private val _state: MutableStateFlow<RocketDetailScreenState> = MutableStateFlow(
        RocketDetailScreenState(
            id = id,
            name = name,
            detail = rocketDetail,
        )
    )
    override val state: StateFlow<RocketDetailScreenState> = _state

    override fun send(action: Unit) {
        Log.d(TAG, "send: $action")
    }

    companion object {
        private const val TAG = "RocketDetailViewModel"
    }
}
