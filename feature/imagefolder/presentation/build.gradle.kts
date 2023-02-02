import Dependencies.AndroidX
import Dependencies.Compose
import Dependencies.Dagger
import Dependencies.Test

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.androidx_compose
    }
    kotlin.sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
}

dependencies {
    implementation(project(":library:udfmvi"))
    implementation(project(":library:composenav"))
    implementation(project(":library:httpnetwork:domain"))
    implementation(project(":feature:imagefolder:domain"))
    implementation(project(":feature:imagefolder:domain:di"))
    implementation(AndroidX.AppCompat)
    implementation(AndroidX.Fragment)
    implementation(Compose.Navigation)
    implementation(Compose.UiMaterial)
    implementation(Compose.Coil)
    implementation(Compose.NavAnimations)

    implementation(Dagger.HiltAndroid)
    implementation(Dagger.HiltComposeNav)
    kapt(Dagger.HiltAndroidCompiler)
    kapt(Dagger.HiltCompiler)

    testImplementation(project(":library:test"))
    testImplementation(Test.Junit)
    testImplementation(Test.Coroutines)
    testImplementation(Test.Runner)
    testImplementation(Test.Mockk)
    testImplementation(Test.Turbine)
}
