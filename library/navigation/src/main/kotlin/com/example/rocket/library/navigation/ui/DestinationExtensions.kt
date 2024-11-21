package com.example.rocket.library.navigation.ui

import com.example.rocket.library.navigation.ui.model.RouteParameterKey
import com.example.rocket.library.navigation.ui.model.RouteParameterValue

/**
 * Create a destination path with placeholders for parameters.
 * Mainly useful when overriding the [Destination.createDestinationPath] method.
 *
 * @param paramKeys a list of parameter keys
 * @return the destination path with parameter placeholders
 */
fun Destination.createDestinationPathWithParams(paramKeys: List<RouteParameterKey>) = createPathWithParams(
    destinationName,
    paramKeys.associateWith { "{$it}" },
)

/**
 * Create a route to destination with parameters.
 * Useful when navigating to a destination with parameters.
 *
 * @param paramMap a map of parameters
 * @return the route to this destination with parameters
 */
fun Destination.createRouteWithParams(paramMap: Map<RouteParameterKey, RouteParameterValue>) = createPathWithParams(
    destinationName,
    paramMap,
)

/**
 * Create a path with parameters.
 * Useful for creating deep links or destination routes with parameters.
 *
 * @param basePath the base path
 * @param paramMap a map of parameters
 * @return the path with parameters
 */
fun createPathWithParams(basePath: String, paramMap: Map<RouteParameterKey, RouteParameterValue>): String {
    return if (paramMap.isEmpty()) {
        basePath
    } else {
        basePath + paramMap.entries.joinToString(
            separator = "&",
            prefix = "?",
        ) { (key, value) ->
            "$key=$value"
        }
    }
}
