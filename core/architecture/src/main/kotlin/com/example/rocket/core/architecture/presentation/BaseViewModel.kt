package com.example.rocket.core.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * A base abstraction for ViewModels.
 * It provides properties and methods to support the MVI pattern. It also provides a way to send navigation and UI events.
 *
 * @param UiState generic type representing the state of the UI
 * @param UiAction generic type representing the action that can be performed by the UI
 * @param NavigationEvent generic type representing the navigation event
 * @param UiEvent generic type representing the UI event
 */
abstract class BaseViewModel<UiState : Any, UiAction : Any, NavigationEvent : Any, UiEvent : Any> : ViewModel() {

    abstract val state: StateFlow<UiState>

    private val mutableNavigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    val navigationEvent: SharedFlow<NavigationEvent> = mutableNavigationEvent

    private val mutableUiEvent = MutableSharedFlow<UiEvent>(extraBufferCapacity = 1)
    val uiEvent: SharedFlow<UiEvent> = mutableUiEvent

    protected fun navigateTo(navigationEvent: NavigationEvent) {
        mutableNavigationEvent.tryEmit(navigationEvent)
    }

    protected fun sendEvent(uiEvent: UiEvent) {
        mutableUiEvent.tryEmit(uiEvent)
    }

    abstract fun send(action: UiAction)

    protected fun <T> Flow<T>.toUiState(getDefaultState: () -> T): StateFlow<T> = stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(SUBSCRIPTION_TIMEOUT),
        initialValue = getDefaultState(),
    )

    companion object {
        private const val SUBSCRIPTION_TIMEOUT = 5_000L
    }
}
