package com.remindme.app.splashscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.remindme.app.navigation.Navigation
import com.remindme.app.ui.theme.RemindMeTheme
import com.remindme.app.ui.theme.Teal700

@Composable
fun ShowSplashScreen(
  navController: NavController,
  viewModel: SplashViewModel = SplashViewModel()
) {

  viewModel.applyAction(SplashViewAction.Launch)
  HandleViewState(navController = navController, viewModel = viewModel)

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

@Composable
private fun HandleViewState(
  navController: NavController,
  viewModel: SplashViewModel
) {
  when (val state = viewModel.viewState.collectAsState(initial = null).value) {
    null, SplashViewState.Default -> {
      Log.i("MVI", "HandleViewState: SplashViewState.Default")
    }
    is SplashViewState.Timeout -> {
      Log.i("MVI", "HandleViewState: SplashViewState.Timeout")
      navController.navigate(Navigation.Routes.LOGIN) {
        popUpTo(Navigation.Routes.SPLASH) {
          inclusive = true
        }
      }
    }
  }
}

@Preview
@Composable
private fun ShowSplashScreenPreview() {
  ShowSplashScreen(rememberNavController())
}