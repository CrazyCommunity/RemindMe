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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remindme.app.ui.theme.RemindMeTheme
import java.util.*

@Composable
fun ShowTimelineScreen(navController: NavController) {
  RemindMeTheme {
    Surface {
      Column(
        modifier = Modifier
          .verticalScroll(rememberScrollState())
          .fillMaxSize()
      ) {

        Spacer(modifier = Modifier.size(20.dp))

        TimelineListGroupView(
          list = listOf(
            Reminder(
              "Dark Soy Sauce",
              "Short notes to be added here...",
              Date(),
              Date(),
              Reminder.Settings(
                emptyList(), Reminder.Notify.Default
              )
            ),
            Reminder(
              "Calpol Syrup",
              "Short notes to be added here...",
              Date(),
              Date(),
              Reminder.Settings(
                emptyList(), Reminder.Notify.Default
              )
            )
          ), timelineState = TimelineState.EXPIRED
        )

        TimelineListGroupView(
          list = listOf(
            Reminder(
              "Dark Soy Sauce",
              "Short notes to be added here...",
              Date(),
              Date(),
              Reminder.Settings(
                emptyList(), Reminder.Notify.Default
              )
            )
          ), timelineState = TimelineState.TODAY
        )

        TimelineListGroupView(
          list = listOf(
            Reminder(
              "Dark Soy Sauce",
              "Short notes to be added here...",
              Date(),
              Date(),
              Reminder.Settings(
                emptyList(), Reminder.Notify.Default
              )
            )
          ), timelineState = TimelineState.TOMORROW
        )

        TimelineListGroupView(
          list = listOf(
            Reminder(
              "Dark Soy Sauce",
              "Short notes to be added here...",
              Date(),
              Date(),
              Reminder.Settings(
                emptyList(), Reminder.Notify.Default
              )
            )
          ), timelineState = TimelineState.THIS_WEEK
        )

        TimelineListGroupView(
          list = listOf(
            Reminder(
              "Dark Soy Sauce",
              "Short notes to be added here...",
              Date(),
              Date(),
              Reminder.Settings(
                emptyList(), Reminder.Notify.Default
              )
            )
          ), timelineState = TimelineState.LATER
        )

      }
    }
  }
}

@Preview
@Composable
private fun ShowTimelineScreenPreview() {
  ShowTimelineScreen(rememberNavController())
}