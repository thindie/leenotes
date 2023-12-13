object Dependencies {

    object Compose {
        private const val composeBomVersion = "2023.06.01"
        const val composeVersion = "1.5.1"

        const val bom = "androidx.compose:compose-bom:$composeBomVersion"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val material3 = "androidx.compose.material3:material3"

        const val navigation = "androidx.navigation:navigation-compose:2.5.3"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val manifest = "androidx.compose.ui:ui-test-manifest"

    }

    object Lifecycle {
        const val core = "androidx.core:core-ktx:1.10.1"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
        const val activityCompose = "androidx.activity:activity-compose:1.7.2"
        const val сomposeRuntime = "androidx.compose.runtime:runtime-android:1.5.0"
        const val сomposeLifecycle = "androidx.lifecycle:lifecycle-runtime-compose:2.6.1"

    }

    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4"
    }

    object Dagger {
        private const val daggerVersion = "2.48"

        const val dagger  = "com.google.dagger:dagger-android:$daggerVersion"
        const val annotationProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
        const val annotationProcessorCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Accompanist {
        private const val accompanistVersion = "0.31.2-alpha"

        const val animationNavigation =
            "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion"

        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
    }


    object Room {
        private const val roomVersion = "2.6.0-alpha03"

        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomPaging = "androidx.room:room-paging:$roomVersion"
        const val roomCoroutines = "androidx.room:room-ktx:$roomVersion"
    }

    object Gson {
        const val gson = "com.google.code.gson:gson:2.10.1"
    }

    object Utils {
        const val desugaring = "com.android.tools:desugar_jdk_libs:2.0.3"
    }
}