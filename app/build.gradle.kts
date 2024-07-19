plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.example.g_evolve"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.g_evolve"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // retrofit dependency
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // RxJava2 Adapter
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    // Logging Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

//    glide image loader
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
}