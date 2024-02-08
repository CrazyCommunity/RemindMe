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

package com.remindme.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.remindme.app.screens.login.ShowLoginScreen
import com.remindme.app.screens.login.ShowOTPScreen
import com.remindme.app.screens.timeline.ShowTimelineScreen

@Composable
fun NavigateToLoginScreen(navController: NavController) {
    ShowLoginScreen(navController)
}

@Composable
fun NavigateToTimelineScreen() {
    ShowTimelineScreen()
}

@Composable
fun NavigationToEnterOTPScreen(navController: NavController) {
    ShowOTPScreen(navController = navController)
}