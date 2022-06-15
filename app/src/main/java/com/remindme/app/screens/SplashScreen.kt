package com.remindme.app.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remindme.app.R
import com.remindme.app.ui.theme.RemindMeTheme
import com.remindme.app.ui.theme.Teal700
import kotlinx.coroutines.delay

@Composable
fun ShowSplashScreen(navController: NavController) {
  val scale = remember { Animatable(0f) }

  // Animation
  LaunchedEffect(key1 = true) {
    scale.animateTo(
      targetValue = 0.7f,
      // tween Animation
      animationSpec = tween(
        durationMillis = 800,
        easing = {
          OvershootInterpolator(4f).getInterpolation(it)
        })
    )
    // Customize the delay time
    delay(3000L)
    navController.navigate("login_screen") {
      popUpTo("splash_screen") {
        inclusive = true
      }
    }
  }

  RemindMeTheme {
    Surface(color = Teal700) {
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_appicon),
          contentDescription = "Application Icon",
          modifier = Modifier.size(120.dp, 120.dp)
        )
        Spacer(modifier = Modifier.size(0.dp, 25.dp))
        Text(
          text = stringResource(R.string.app_name),
          fontSize = 36.sp,
          color = Color.White
        )
      }
    }
  }
}

@Preview
@Composable
private fun ShowSplashScreenPreview() {
  ShowSplashScreen(rememberNavController())
}