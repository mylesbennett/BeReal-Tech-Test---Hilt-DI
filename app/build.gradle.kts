import Dependencies.AndroidX
import Dependencies.Compose
import Dependencies.Dagger
import Dependencies.Kotlin
import Dependencies.Test

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdk = Config.compileSdkVersion
    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.androidx_compose
    }
}

dependencies {
    implementation(project(":feature:imagefolder:presentation"))
    implementation(AndroidX.CoreKtx)
    implementation(AndroidX.AppCompat)
    implementation(AndroidX.Material)
    implementation(AndroidX.ConstraintLayout)

    implementation(Kotlin.Stdlib)
    implementation(Kotlin.Coroutines)

    implementation(Compose.Navigation)
    implementation(Compose.NavAnimations)

    implementation(Dagger.HiltAndroid)
    kapt(Dagger.HiltAndroidCompiler)
    kapt(Dagger.HiltCompiler)

    testImplementation(Test.Junit)

    androidTestImplementation(Test.JunitExt)
    androidTestImplementation(Test.EspressoCore)
}
