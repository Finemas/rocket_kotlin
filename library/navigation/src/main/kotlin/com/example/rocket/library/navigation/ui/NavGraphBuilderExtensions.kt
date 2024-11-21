package com.example.rocket.library.navigation.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.rocket.library.navigation.ui.model.DeepLink

/**
 * Adds a [Destination] to the NavGraph
 *
 * @param route the [Destination] to add
 * @param arguments the arguments for the destination
 * @param content the composable content for the destination
 */
fun NavGraphBuilder.destination(
    route: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route.createDestinationPath(),
        arguments = arguments,
        content = content,
    )
}

/**
 * Creates a destination for each deeplink in this NavGraphBuilder. The deeplink's navigation block will be called with the given arguments
 * when the destination is opened (by the deeplink).
 */
fun NavGraphBuilder.setupDeepLinks(navController: NavHostController, deepLinks: Set<DeepLink>) {
    deepLinks.forEach { deeplink ->
        composable(
            route = deeplink.fullDeepLinkPath(),
            deepLinks = listOf(navDeepLink { this.uriPattern = deeplink.fullDeepLinkPath() }),
        ) { backStackEntry ->
            val deeplinkArgs: Map<String, String> =
                deeplink.parameterKeys
                    .associateWith { key -> backStackEntry.arguments?.getString(key) ?: "" }
                    .filterValues { value -> value.isNotEmpty() }
            navController.popBackStack()
            deeplink.navigationBlock.invoke(navController, deeplinkArgs)
        }
    }
}

// TODO: Task - 95563 - Reintroduce bottom sheet navigation (as in KMM-POC) once officially supported for material3
