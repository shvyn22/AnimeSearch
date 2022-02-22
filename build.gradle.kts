buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Gradle.gradle)
        classpath(Dependencies.Kotlin.kotlinGradlePlugin)
        classpath(Dependencies.Navigation.navigationSafeArgs)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}