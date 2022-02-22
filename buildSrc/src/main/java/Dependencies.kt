object Dependencies {

    object Kotlin {
        private const val kotlinVersion = "1.6.10"
        private const val ktxVersion = "1.7.0"

        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val ktxCore = "androidx.core:core-ktx:$ktxVersion"

        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object AppCompat {
        private const val appCompatVersion = "1.4.0"

        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    }

    object UI {
        private const val materialVersion = "1.6.0-alpha01"
        private const val constraintLayoutVersion = "2.1.2"

        const val material = "com.google.android.material:material:$materialVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    }

    object Fragment {
        private const val fragmentVersion = "1.4.0"

        const val fragment = "androidx.fragment:fragment-ktx:$fragmentVersion"
    }

    object Lifecycle {
        private const val lifecycleVersion = "2.4.0"

        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    }

    object Navigation {
        private const val navigationVersion = "2.3.5"

        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
    }

    object Room {
        private const val roomVersion = "2.4.0"

        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val roomRx = "androidx.room:room-rxjava3:$roomVersion"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.6.0"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object RxJava {
        private const val rxJavaVersion = "3.0.0"

        const val rxJava = "io.reactivex.rxjava3:rxjava:$rxJavaVersion"
        const val rxJavaAndroid = "io.reactivex.rxjava3:rxandroid:$rxJavaVersion"
    }

    object Dagger {
        private const val daggerVersion = "2.40.5"

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object DataStore {
        private const val dataStoreVersion = "1.0.0"

        const val preferencesDataStore = "androidx.datastore:datastore-preferences:$dataStoreVersion"
        const val preferencesDataStoreRx = "androidx.datastore:datastore-preferences-rxjava3:$dataStoreVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.8.1"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava:$retrofitVersion"
    }

    object Glide {
        private const val glideVersion = "4.12.0"

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    }

    object Gradle {
        private const val gradleVersion = "7.0.4"

        const val gradle = "com.android.tools.build:gradle:$gradleVersion"
    }
}