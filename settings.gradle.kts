rootProject.name = "BlackInk"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    //includeBuild("BlackInk")
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
    plugins {
        kotlin("jvm") version "2.2.20"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
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


include(":composeApp")
include(":core:presentation")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":feature:auth:presentation")
include(":feature:chat:presentation")
include(":feature:chat:domain")
include(":feature:chat:data")
include(":feature:auth:domain")
include(":feature:chat:database")
include(":feature:chat:presentationn")
include(":project:build_logic")
include(":core:presentation")
