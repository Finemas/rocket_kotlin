package com.example.rocket.convention.plugin

import com.android.build.gradle.LibraryExtension
import com.example.rocket.convention.PluginWrapper
import com.example.rocket.convention.extention.configureDetekt
import com.example.rocket.convention.extention.detektPlugins
import com.example.rocket.convention.extention.getLibrary
import com.example.rocket.convention.extention.getPluginId
import com.example.rocket.convention.extention.getVersion
import com.example.rocket.convention.extention.implementation
import com.example.rocket.convention.extention.jvmTarget
import com.example.rocket.convention.extention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class LibraryPlugin : Plugin<Project> by PluginWrapper factory {
    apply(plugin = versionCatalog.getPluginId("android-library"))
    apply(plugin = versionCatalog.getPluginId("jetbrains-kotlin-android"))
    apply(plugin = versionCatalog.getPluginId("detekt"))

    val libraryExtension = extensions.findByType<LibraryExtension>()
    libraryExtension?.apply {
        namespace = "com.example.rocket.${project.name.replace("-", "")}"
        compileSdk = versionCatalog.getVersion("compileSdk").toInt()

        defaultConfig {
            minSdk = versionCatalog.getVersion("minSdk").toInt()
        }

        dependencies {
            implementation(versionCatalog.getLibrary("koin-core"))
            implementation(versionCatalog.getLibrary("koin-compose"))

            detektPlugins(versionCatalog.getLibrary("detekt-formatting"))
            detektPlugins(versionCatalog.getLibrary("detekt-compose"))
        }
    } ?: error("Library extension not found")

    val kotlinExtension = extensions.findByType<KotlinAndroidProjectExtension>()
    kotlinExtension?.apply {
        jvmToolchain(versionCatalog.jvmTarget)
    } ?: error("Kotlin extension not found")

    configureDetekt()
}
