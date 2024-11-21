package com.example.rocket.library.navigation.ui

import com.example.rocket.library.navigation.ui.model.DeepLink

private const val SCHEME_DELIMITER = "://"
private const val DEFAULT_SCHEME = "drmaxpoc://"

/**
 * Returns the full deep link path with parameters in curly braces.
 */
fun DeepLink.fullDeepLinkPath(): String {
    val baseWithHost = if (basePath.contains(SCHEME_DELIMITER)) {
        basePath
    } else {
        "$DEFAULT_SCHEME$basePath"
    }
    return createPathWithParams(
        baseWithHost,
        parameterKeys.associateWith { "{$it}" },
    )
}
