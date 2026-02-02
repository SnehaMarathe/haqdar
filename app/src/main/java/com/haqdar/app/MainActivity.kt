package com.haqdar.app

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.haqdar.app.ui.navigation.AppNavHost
import com.haqdar.app.ui.theme.HaqdarTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    setContent {
      HaqdarTheme {
        AppNavHost()
      }
    }
  }
}
