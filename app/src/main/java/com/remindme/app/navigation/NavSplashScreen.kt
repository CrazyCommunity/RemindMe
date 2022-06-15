package com.remindme.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.remindme.app.splashscreen.ShowSplashScreen

@Composable
fun NavigateToSplashScreen(navController: NavController) {
  ShowSplashScreen(navController)
}