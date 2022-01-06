buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Gradle.gradle)
        classpath(Dependencies.Kotlin.kotlinGradlePlugin)
        classpath(Dependencies.Hilt.hiltGradlePlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}