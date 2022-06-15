package com.remindme.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.remindme.app.navigation.AppNavigation
import com.remindme.app.ui.theme.RemindMeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      RemindMeTheme {
        AppNavigation()
      }
    }
  }
}