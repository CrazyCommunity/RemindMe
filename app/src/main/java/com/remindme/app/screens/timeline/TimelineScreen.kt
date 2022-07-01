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

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remindme.app.ui.theme.RemindMeTheme
import com.remindme.app.ui.theme.Teal700
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowTimelineScreen(navController: NavController) {
  RemindMeTheme {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
      scaffoldState = scaffoldState,
      topBar = { AppTopBar(scaffoldState, scope) },
      floatingActionButtonPosition = FabPosition.End,
      floatingActionButton = {
        FloatingActionButton(onClick = {
          // TODO open Add new bottom sheet
        }) {
          Icon(Icons.Filled.Add, null)
        }
      },
      drawerContent = { NavigationDrawerContent() },
      content = { Content() }
    )
  }
}

@Composable
fun AppTopBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
  TopAppBar(
    elevation = 5.dp,
    title = { Text("Timeline") },
    backgroundColor = Teal700,
    navigationIcon = {
      IconButton(onClick = {
        scope.launch {
          scaffoldState.drawerState.open()
        }
      }) {
        Icon(Icons.Filled.Menu, null)
      }
    },
    actions = {
      IconButton(onClick = {
        // TODO open search screen
      }) {
        Icon(Icons.Filled.Search, null)
      }
    }
  )
}

@Composable
fun Content() {
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
            title = "Dark Soy Sauce",
            notes = "Short notes to be added here...",
            purchasedOn = Date(),
            expiry = Date(),
            Reminder.Settings(
              emptyList(), Reminder.Notify.Default
            )
          ),
          Reminder(
            title = "Calpol Syrup",
            notes = "Short notes to be added here...",
            purchasedOn = Date(),
            expiry = Date(),
            Reminder.Settings(
              emptyList(), Reminder.Notify.Default
            )
          )
        ), timelineState = TimelineState.EXPIRED
      )

      TimelineListGroupView(
        list = listOf(
          Reminder(
            title = "Dark Soy Sauce",
            notes = "Short notes to be added here...",
            purchasedOn = Date(),
            expiry = Date(),
            Reminder.Settings(
              emptyList(), Reminder.Notify.Default
            )
          )
        ), timelineState = TimelineState.TODAY
      )

      TimelineListGroupView(
        list = listOf(
          Reminder(
            title = "Dark Soy Sauce",
            notes = "Short notes to be added here...",
            purchasedOn = Date(),
            expiry = Date(),
            Reminder.Settings(
              emptyList(), Reminder.Notify.Default
            )
          )
        ), timelineState = TimelineState.TOMORROW
      )

      TimelineListGroupView(
        list = listOf(
          Reminder(
            title = "Dark Soy Sauce",
            notes = "Short notes to be added here...",
            purchasedOn = Date(),
            expiry = Date(),
            Reminder.Settings(
              emptyList(), Reminder.Notify.Default
            )
          )
        ), timelineState = TimelineState.THIS_WEEK
      )

      TimelineListGroupView(
        list = listOf(
          Reminder(
            title = "Dark Soy Sauce",
            notes = "Short notes to be added here...",
            purchasedOn = Date(),
            expiry = Date(),
            Reminder.Settings(
              emptyList(), Reminder.Notify.Default
            )
          )
        ), timelineState = TimelineState.LATER
      )

    }
  }
}

@Preview
@Composable
private fun ShowTimelineScreenPreview() {
  ShowTimelineScreen(rememberNavController())
}