import Dependencies.Ktor

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":library:httpnetwork:domain"))
                implementation(Ktor.Core)
                implementation(Ktor.ContentNegotiation)
                implementation(Ktor.JsonSerialization)
                implementation(Ktor.Logging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.ktorClientAndroid)
            }
        }
        val androidTest by getting
    }
}

android {
    compileSdk = Config.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
    }
}
