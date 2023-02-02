object Dependencies {

    object Kotlin {
        const val Stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val CoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val Serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization_version}"
        const val JsonSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization_version}"
    }

    object AndroidX {
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val CoreKtx = "androidx.core:core-ktx:1.7.0"
        const val Material = "com.google.android.material:material:${Versions.material}"
        const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_viewmodel}"
        const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle}"
        const val Fragment = "androidx.fragment:fragment-ktx:${Versions.androidx_fragment}"
        const val NavFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    }

    object Compose {
        const val UiMaterial = "androidx.compose.material:material:${Versions.androidx_compose}"
        const val ThemeAdapter = "com.google.android.material:compose-theme-adapter:${Versions.compose_theme_adapter}"
        const val Ui = "androidx.compose.ui:ui:${Versions.androidx_compose}"
        const val ToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.androidx_compose}"
        const val Navigation = "androidx.navigation:navigation-compose:${Versions.compose_nav}"
        const val LifeCycle = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.compose_lifecycle}"
        const val Coil = "io.coil-kt:coil-compose:${Versions.coil}"
        const val NavAnimations = "com.google.accompanist:accompanist-navigation-animation:${Versions.compose_animations}"
    }

    object Dagger {
        const val HiltWorkManager = "androidx.hilt:hilt-work:${Versions.hilt}"
        const val HiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt_plugin}"
        const val HiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_compiler}"
        const val HiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
        const val HiltComposeNav = "androidx.hilt:hilt-navigation-compose:${Versions.hilt}"
    }

    object Ktor {
        const val Core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val ContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val JsonSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val Json = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val Logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val ktorClientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val ktorClientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val ktorClientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val ktorAuth = "io.ktor:ktor-client-auth:${Versions.ktor}"
    }

    object Test {
        const val EspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val Junit = "junit:junit:4.13.2"
        const val JunitExt = "androidx.test.ext:junit:1.1.3"
        const val Runner = "androidx.test:runner:${Versions.androidx_test}"
        const val Mockk = "io.mockk:mockk:${Versions.mockk}"
        const val Turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    }
}
