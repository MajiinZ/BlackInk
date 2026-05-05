plugins {
    'kotlin-dsl'
}

group = "com.mz.convention.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JavaVersion.VERSION_17
    }

}