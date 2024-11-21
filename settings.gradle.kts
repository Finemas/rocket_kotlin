enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Rocket"
includeBuild("gradle-config")
include(":app")
include(":feature:rocket-list")
include(":feature:rocket-detail")
include(":library:rocket-list")
include(":library:rocket-detail")
include(":library:networking")
include(":library:ui-core")
include(":library:navigation")
include(":core:architecture")
