package com.remindme.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

      }
    }
  }
}

@Preview
@Composable
private fun ShowLoginScreenPreview() {
  ShowLoginScreen()
}