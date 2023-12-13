// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.androidApplication) version Plugins.androidApplicationVersion apply false
    id(Plugins.kotlinAndroid) version Plugins.kotlinAndroidVersion apply false
    id(Plugins.kotlinKapt) version Plugins.kotlinAndroidVersion apply false
}
