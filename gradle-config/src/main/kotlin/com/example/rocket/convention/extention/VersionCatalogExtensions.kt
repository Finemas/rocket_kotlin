package com.example.rocket.convention.extention

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.findByType

/**
 * Currently the only way how to access version catalogs. Should be fixed in
 * future versions of Gradle.
 * [Issue](https://github.com/gradle/gradle/issues/15383)
 */
internal val Project.versionCatalog: VersionCatalog
    get() = extensions.findByType<VersionCatalogsExtension>()
        ?.named("libs")
        ?: error("No ${VersionCatalogsExtension::class.simpleName} named 'libs' found.")

internal fun VersionCatalog.getPluginId(alias: String): String {
    val optional = findPlugin(alias)

    if (optional.isPresent) {
        return optional.get().get().pluginId
    } else {
        error("No plugin with alias $alias found in version catalog.")
    }
}

internal fun VersionCatalog.getVersion(alias: String): String {
    val optional = findVersion(alias)

    if (optional.isPresent) {
        return optional.get().displayName
    } else {
        error("No version with alias $alias found in version catalog.")
    }
}

internal fun VersionCatalog.getLibrary(alias: String): Provider<MinimalExternalModuleDependency> {
    val optional = findLibrary(alias)

    if (optional.isPresent) {
        return optional.get()
    } else {
        error("No library with alias $alias found in version catalog.")
    }
}

internal val VersionCatalog.minSdk: Int
    get() = getVersion("minSdk").toInt()

internal val VersionCatalog.compileSdk: Int
    get() = getVersion("compileSdk").toInt()

internal val VersionCatalog.targetSdk: Int
    get() = getVersion("targetSdk").toInt()

internal val VersionCatalog.jvmTarget: Int
    get() = getVersion("jvmTarget").toInt()
