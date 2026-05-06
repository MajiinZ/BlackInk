rootProject.name = "BlackInk"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


dependencyResolutionManagement {
    //includeBuild("BlackInk")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }


versionCatalogs {
    create("libs"){
        from(files("../gradle/libs.versions.toml"))
    }
}


include(":convention")