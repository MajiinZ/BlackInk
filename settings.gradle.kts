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
        kotlin("jvm") version "2.1.2"
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

    }
}

plugins{
    id("org.gradle.toolchains.foojay-resolver") version "1.0.0"
}

include(":composeApp")
include(":core:presentation")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":feature:chat:presentation")
include(":feature:chat:domain")
include(":feature:chat:data")
include(":feature:auth:domain")
include(":feature:auth:presentation")
include(":feature:chat:database")

