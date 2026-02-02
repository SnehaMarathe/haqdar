package com.haqdar.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.haqdar.app.HaqdarApp
import com.haqdar.app.ui.screens.BenefitsScreen
import com.haqdar.app.ui.screens.DashboardScreen
import com.haqdar.app.ui.screens.OnboardingScreen

@Composable
fun AppNavHost() {
  val nav = rememberNavController()
  val app = LocalContext.current.applicationContext as HaqdarApp

  NavHost(navController = nav, startDestination = NavRoutes.Onboarding) {
    composable(NavRoutes.Onboarding) {
      OnboardingScreen(app = app, onDone = { nav.navigate(NavRoutes.Dashboard) })
    }
    composable(NavRoutes.Dashboard) {
      DashboardScreen(app = app, onOpenBenefits = { nav.navigate(NavRoutes.Benefits) })
    }
    composable(NavRoutes.Benefits) {
      BenefitsScreen(app = app)
    }
  }
}
