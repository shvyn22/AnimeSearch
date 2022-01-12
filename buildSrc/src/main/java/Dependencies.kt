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

    object Tests {
        private const val junitVersion = "4.13.2"
        private const val junitExtVersion = "1.1.3"
        private const val androidXTestCoreVersion = "1.4.0"
        private const val hamcrestVersion = "1.3"
        private const val archTestingVersion = "2.1.0"
        private const val espressoVersion = "3.4.0"

        const val junit = "junit:junit:$junitVersion"
        const val junitExt = "androidx.test.ext:junit:$junitExtVersion"
        const val androidXTestCore = "androidx.test:core-ktx:$androidXTestCoreVersion"
        const val hamcrest = "org.hamcrest:hamcrest-all:$hamcrestVersion"
        const val archTesting = "androidx.arch.core:core-testing:$archTestingVersion"

        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"
    }

    object Fragment {
        private const val fragmentVersion = "1.4.0"

        const val fragment = "androidx.fragment:fragment-ktx:$fragmentVersion"
        const val fragmentTesting = "androidx.fragment:fragment-testing:$fragmentVersion"
    }

    object Lifecycle {
        private const val lifecycleVersion = "2.4.0"

        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    }

    object Navigation {
        private const val navigationVersion = "2.3.5"

        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        const val navigationTesting = "androidx.navigation:navigation-testing:$navigationVersion"

        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
    }

    object Room {
        private const val roomVersion = "2.4.0"

        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.6.0"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val coroutinesTesting = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
    }

    object Hilt {
        private const val hiltVersion = "2.37"
        private const val hiltAndroidXVersion = "1.0.0-alpha03"

        const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val hiltAndroidCompiler = "androidx.hilt:hilt-compiler:$hiltAndroidXVersion"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltAndroidXVersion"
        const val hiltTesting = "com.google.dagger:hilt-android-testing:$hiltVersion"

        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    }

    object DataStore {
        private const val dataStoreVersion = "1.0.0"

        const val preferencesDataStore = "androidx.datastore:datastore-preferences:$dataStoreVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.8.1"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
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