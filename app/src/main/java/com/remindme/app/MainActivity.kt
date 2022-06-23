/*
 * Copyright 2022 CrazyCommunity
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.remindme.app

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.remindme.app.navigation.AppNavigation
import com.remindme.app.screens.splash.SplashViewAction
import com.remindme.app.screens.splash.SplashViewModel
import com.remindme.app.screens.splash.SplashViewState
import com.remindme.app.ui.theme.RemindMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val viewModel: SplashViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      RemindMeTheme {
        AppNavigation()
      }
    }
    handleSplashScreenTask()
  }

  private fun handleSplashScreenTask() {

    val content: View = findViewById(android.R.id.content)
    viewModel.applyAction(SplashViewAction.Launch)

    content.viewTreeObserver.addOnPreDrawListener(
      object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
          return if (viewModel.viewState.value is SplashViewState.LoadingComplete) {
            content.viewTreeObserver.removeOnPreDrawListener(this)
            true
          } else {
            false
          }
        }
      }
    )
  }
}