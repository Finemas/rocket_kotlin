plugins {
    alias(libs.plugins.local.library)
    alias(libs.plugins.local.compose)
}

dependencies {
    implementation(libs.androidx.navigation)
    implementation(projects.core.architecture)
}
