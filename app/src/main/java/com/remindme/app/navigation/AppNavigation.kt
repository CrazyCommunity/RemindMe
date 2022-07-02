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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.LOGIN
    ) {
        composable(route = Navigation.Routes.LOGIN) {
            NavigateToLoginScreen(navController = navController)
        }
        composable(route = Navigation.Routes.OTP_PAGE) {
            NavigationToEnterOTPScreen(navController = navController)
        }
        composable(route = Navigation.Routes.TIMELINE) {
            NavigateToTimelineScreen(navController = navController)
        }
    }
}

object Navigation {
    object Routes {
        const val TIMELINE = "timeline"
        const val LOGIN = "login_page"
        const val OTP_PAGE = "otp_page"
    }
}