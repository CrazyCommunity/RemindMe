package com.remindme.app.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.remindme.app.R
import com.remindme.app.ui.theme.RemindMeTheme
import com.remindme.app.ui.theme.Teal700

@Composable
fun ShowLoginScreen() {
  RemindMeTheme {
    Surface(color = Teal700) {
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "Login Page",
          fontSize = 36.sp,
          color = Color.White
        )
      }
    }
  }
}

@Preview
@Composable
private fun ShowLoginScreenPreview() {
  ShowLoginScreen()
}