plugins {
    alias(libs.plugins.convention.cmp.feature)
}

kotlin {

    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(projects.core.domain)

                implementation(projects.core.domain)
                implementation(projects.feature.chat.domain)
                implementation(projects.feature.chat.database)
                implementation(projects.feature.chat.presentation)
                implementation(projects.feature.auth.domain)
                implementation(projects.feature.auth.presentation)
            }
        }


        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            }
        }


        iosMain {
            dependencies {
               implementation(projects.core.domain)
                implementation(projects.feature.chat.domain)
                implementation(projects.feature.chat.database)
                implementation(projects.feature.chat.presentation)
                implementation(projects.feature.auth.domain)
                implementation(projects.feature.auth.presentation)

            }
        }
    }

}