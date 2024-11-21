package com.example.rocket.library.navigation.ui

import androidx.navigation.NavHostController
import com.example.rocket.library.navigation.ui.model.DeepLink
import com.example.rocket.library.navigation.ui.model.DeepLinkParameterKey
import com.example.rocket.library.navigation.ui.model.DeepLinkParameterValue

/**
 * Builder class for creating deep links
 */
class DeepLinkNavigationBuilder {
    private val _deepLinks = mutableSetOf<DeepLink>()
    val deepLinks: Set<DeepLink> = _deepLinks

    /**
     * Add a deep link to the navigation graph
     *
     * @param path The path of the deep link
     * @param paramKeys The keys of the parameters that the deep link will accept
     * @param navigationBlock The block of code that will be executed when the deep link is navigated to
     * @throws IllegalArgumentException If a deep link with the same path already exists
     */
    fun deepLink(
        path: String,
        paramKeys: List<DeepLinkParameterKey> = emptyList(),
        navigationBlock: NavHostController.(params: Map<DeepLinkParameterKey, DeepLinkParameterValue>) -> Unit,
    ) {
        require(_deepLinks.add(DeepLink(path, paramKeys, navigationBlock))) {
            "Deep link with path: '$path' and parameters: '${paramKeys.joinToString(", ")}' already exists"
        }
    }
}
