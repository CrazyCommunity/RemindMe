package com.remindme.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Navigation.Routes.SPLASH
  ) {
    composable(route = Navigation.Routes.SPLASH) {
      NavigateToSplashScreen(navController = navController)
    }

    composable(route = Navigation.Routes.LOGIN) {
      NavigateToLoginScreen()
    }
  }
}

object Navigation {
  object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
  }
}