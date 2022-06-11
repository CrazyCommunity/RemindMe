package com.remindme.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remindme.app.screens.ShowLoginScreen
import com.remindme.app.screens.ShowSplashScreen
import com.remindme.app.ui.theme.RemindMeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      RemindMeTheme {
        ComposeNavigation()
      }
    }
  }
}

@Composable
private fun ComposeNavigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = "splash_screen"
  ) {
    composable("splash_screen") {
      ShowSplashScreen(navController)
    }
    composable("login_screen") {
      ShowLoginScreen()
    }

  }
}