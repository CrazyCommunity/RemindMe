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

package com.remindme.app.screens.timeline

import com.remindme.app.R

sealed class NavDrawerItem(val route: String, val icon: Int, val title: String) {
  object Timeline : NavDrawerItem("timeline", R.drawable.ic_timeline, "Timeline")
  object Settings : NavDrawerItem("settings", R.drawable.ic_settings, "Settings")
  object Logout : NavDrawerItem("logout", R.drawable.ic_logout, "Logout")
  object TermsOfUse : NavDrawerItem("terms_of_use", R.drawable.ic_terms, "Terms of Use")
  object PrivacyPolicy : NavDrawerItem("privacy_policy", R.drawable.ic_privacy, "Privacy Policy")
  object EmailUs : NavDrawerItem("email_us", R.drawable.ic_email, "Email Us")
}
