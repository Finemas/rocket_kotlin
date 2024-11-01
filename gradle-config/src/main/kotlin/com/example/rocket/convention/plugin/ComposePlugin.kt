package com.example.rocket.convention.plugin

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.example.rocket.convention.PluginWrapper
import com.example.rocket.convention.extention.getPluginId
import com.example.rocket.convention.extention.implementation
import com.example.rocket.convention.extention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.compose.ComposePlugin as JetbrainsComposePlugin

class ComposePlugin : Plugin<Project> by PluginWrapper factory {
    apply(plugin = versionCatalog.getPluginId("compose-compiler"))
    apply(plugin = versionCatalog.getPluginId("compose"))

    val compose = dependencies.extensions.findByType<JetbrainsComposePlugin.Dependencies>()
        ?: error("Compose dependencies not found")

    dependencies {
        implementation(compose.material3)
        implementation(compose.ui)
        implementation(compose.preview)
        implementation(compose.uiTooling)
        implementation(compose.runtime)
        implementation(compose.foundation)
    }

    val libraryExtension = extensions.findByType<BaseExtension>()
    libraryExtension?.apply {
        buildFeatures.compose = true
    } ?: error("Library extension not found")
}