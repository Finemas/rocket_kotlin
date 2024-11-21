plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.local.compose)
}

android {
    namespace = "com.example.rocket"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.rocket"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val detektDir = rootDir.path
val projectDir = project.projectDir.path

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true

    config.setFrom("$detektDir/detekt-config.yml")
    source.setFrom("$projectDir/src")
}

kotlin {
    jvmToolchain(libs.versions.jvmTarget.get().toInt())
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.navigation)

    implementation(libs.koin.compose)
    implementation(libs.koin.core)
    implementation(libs.kotlinx.serialization.core)

    implementation(projects.feature.rocketList)
    implementation(projects.feature.rocketDetail)
    implementation(projects.library.rocketList)
    implementation(projects.library.networking)
    implementation(projects.library.uiCore)
    implementation(projects.library.navigation)
    implementation(projects.core.architecture)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.compose)
}