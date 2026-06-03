@file:Suppress("UnstableApiUsage")

rootProject.name = "BlackInk"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "2.1.0"
    }
}



dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()

    }
}



include(":composeApp")
include(":core:presentation")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":feature:chat:presentation")
include(":feature:chat:domain")
include(":feature:chat:data")
include(":feature:chat:database")
include(":feature:auth:domain")
include(":feature:auth:presentation")

//include(":feature:auth:data")
