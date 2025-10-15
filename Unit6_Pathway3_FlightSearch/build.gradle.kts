// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.android.library")    version "8.7.3" apply false
}
buildscript {
    extra.apply {
        set("nav_version", "2.8.4")
        set("room_version", "2.6.1")
    }
}
