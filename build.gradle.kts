// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(GradleConfig.Android)
        classpath(GradleConfig.Kotlin)
        classpath (GradleConfig.Hilt)
     }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    afterEvaluate {
        // Remove log pollution until Android support in KMP improves.
        project.extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()?.let { kmpExt ->
            kmpExt.sourceSets.removeAll { it.name == "androidAndroidTestRelease" }
            kmpExt.sourceSets.removeAll { it.name == "androidTestFixtures" }
            kmpExt.sourceSets.removeAll { it.name == "androidTestFixturesDebug" }
            kmpExt.sourceSets.removeAll { it.name == "androidTestFixturesRelease" }
        }
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
