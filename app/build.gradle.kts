plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
}

android {
    namespace = Project.nameSpace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
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
        kotlinCompilerExtensionVersion = Dependencies.Compose.composeVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}"
            excludes += "win32-x86-64/attach_hotspot_windows.dll"
            excludes += "win32-x86/attach_hotspot_windows.dll"
            excludes += "META-INF/licenses/ASM"
        }
    }
}

dependencies {
    // Java 8+
   // implementation(Dependencies.Utils.desugaring)
    // Lifecycle
    implementation(Dependencies.Lifecycle.core)
    implementation(Dependencies.Lifecycle.lifecycleRuntime)
    implementation(Dependencies.Lifecycle.activityCompose)
    implementation(Dependencies.Lifecycle.сomposeLifecycle)


    // Compose
    implementation(platform(Dependencies.Compose.bom))
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.constraintLayout)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.hiltNavigation)
    kapt(Dependencies.Dagger.annotationProcessorCompiler)

    // Accompanist
    implementation(Dependencies.Accompanist.animationNavigation)
    implementation(Dependencies.Accompanist.systemUiController)


 

    // Room
    implementation(Dependencies.Room.roomRuntime)
    kapt(Dependencies.Room.roomCompiler)
    implementation(Dependencies.Room.roomPaging)
    implementation(Dependencies.Room.roomCoroutines)


    // Gson
    implementation(Dependencies.Gson.gson)

    // Testing
    testImplementation(Dependencies.Testing.junit)
    androidTestImplementation(Dependencies.Testing.androidJunit)
    androidTestImplementation(Dependencies.Testing.espresso)
    androidTestImplementation(platform(Dependencies.Compose.bom))
    androidTestImplementation(Dependencies.Testing.composeJunit)
    debugImplementation(Dependencies.Compose.tooling)
    debugImplementation(Dependencies.Compose.manifest)

    testImplementation(Testing.testMockk)
    testImplementation(Testing.testKotestRunner)
    testImplementation(Testing.testKotestAssertions)
    implementation(Testing.mockk)
    implementation(Testing.mockkAgent)
    implementation(Testing.kotestRunner)
}