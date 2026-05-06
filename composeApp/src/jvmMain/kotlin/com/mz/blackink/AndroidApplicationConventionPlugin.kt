package com.mz.blackink

import com.mz.blackink.com.configure
import com.sun.source.util.Plugin
import org.gradle.api.Plugin
import com.android.build.api.dsl.ApplicationExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }
            extensions.configure<ApplicationExtension> {
                namespace = "com.mz.blackink"
                compileSdk = libs.findVersion("projectCompileSdkVersion").get().toString.toInt()

                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    minSdk = libs.findVersion("projectMinSdkVersion").get().toString.toInt()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString.toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString.toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()

                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString.toInt()


            }


        }
    }

}