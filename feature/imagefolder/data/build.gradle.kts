import Dependencies.Ktor
import Dependencies.Kotlin
import Dependencies.Test

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version Versions.kotlin
}

version = "1.0"

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":library:httpnetwork:data"))
                implementation(project(":library:httpnetwork:domain"))
                implementation(project(":feature:imagefolder:domain"))
                implementation(Kotlin.Serialization)
                implementation(Ktor.Core)
                implementation(Ktor.ContentNegotiation)
                implementation(Ktor.JsonSerialization)
                implementation(Ktor.Logging)
                implementation(Ktor.ktorAuth)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(Test.Junit)
            }
        }
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
