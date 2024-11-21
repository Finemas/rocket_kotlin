package com.example.rocket.library.navigation.ui

import androidx.navigation.NavController
import com.example.rocket.library.navigation.ui.model.RouteParameterKey
import com.example.rocket.library.navigation.ui.model.RouteParameterValue

/**
 * Navigates to a [Destination]
 *
 * @param destination the destination to navigate to
 * @param params the parameters to pass to the destination
 */
fun NavController.navigateToDestination(
    destination: Destination,
    params: Map<RouteParameterKey, RouteParameterValue> = emptyMap(),
) {
    navigate(destination.createRouteWithParams(params))
}
