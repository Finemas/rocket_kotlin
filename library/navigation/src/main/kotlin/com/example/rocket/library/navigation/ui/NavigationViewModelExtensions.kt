package com.example.rocket.library.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.rocket.core.architecture.presentation.BaseViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersDefinition

/**
 * Get View Model of type [VM] from Koin and observe its [NavigationEvent] to navigate.
 *
 * @param NavigationEvent The Type of navigation event that the ViewModel emits
 * @param VM Type of ViewModel to get
 * @param parameters Parameters to pass to the ViewModel constructor. Must be defined as parametrized bean in Koin module.
 * @param navigateTo Function to navigate to a destination
 * @return [VM] ViewModel instance
 */
@Suppress("LambdaParameterInRestartableEffect")
@Composable
inline fun <NavigationEvent, reified VM : BaseViewModel<*, *, NavigationEvent, *>> navigatingViewModel(
    noinline parameters: ParametersDefinition? = null,
    crossinline navigateTo: VM.(NavigationEvent) -> Unit,
): VM {
    // Type argument error is unfortunately present due to the limitations of ViewModel implementation
    // not being present in PlatformViewModel but in concrete Android implementation of it. It still builds
    val viewModel = koinViewModel<VM>(parameters = parameters)
    LaunchedEffect(key1 = Unit) {
        viewModel.navigationEvent.collect { navigationEvent ->
            with(viewModel) {
                navigateTo(navigationEvent)
            }
        }
    }
    return viewModel
}
