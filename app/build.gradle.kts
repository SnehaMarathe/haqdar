plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.plugin.compose")
  id("com.google.devtools.ksp")
}

android {
  namespace = "com.haqdar.app"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.haqdar.app"
    minSdk = 26
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"
  }

  buildFeatures {
    compose = true
  }
  kotlinOptions {
    jvmTarget = "17"
  }

  packaging {
    resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
  }
}

dependencies {
  val composeBom = platform("androidx.compose:compose-bom:2024.10.00")
  implementation(composeBom)
  androidTestImplementation(composeBom)

  implementation("androidx.activity:activity-compose:1.9.3")
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-tooling-preview")
  debugImplementation("androidx.compose.ui:ui-tooling")

  implementation("androidx.compose.material3:material3:1.3.0")

  // Material Components (needed for XML Material3 themes used by splash/theme)
  implementation("com.google.android.material:material:1.12.0")
  implementation("androidx.navigation:navigation-compose:2.8.3")

  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

  implementation("androidx.room:room-runtime:2.6.1")
  implementation("androidx.room:room-ktx:2.6.1")
  ksp("androidx.room:room-compiler:2.6.1")

  implementation("androidx.work:work-runtime-ktx:2.9.1")

  // Splash screen
  implementation("androidx.core:core-splashscreen:1.0.1")
  implementation("androidx.core:core-ktx:1.13.1")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
