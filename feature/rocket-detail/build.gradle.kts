plugins {
    alias(libs.plugins.local.library)
    alias(libs.plugins.local.compose)
}

dependencies {
    implementation(projects.library.rocketDetail)
    implementation(projects.library.uiCore)
    implementation(projects.core.architecture)
}
