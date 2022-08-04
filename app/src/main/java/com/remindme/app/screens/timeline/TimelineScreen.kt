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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.remindme.app.navigation.Navigation
import com.remindme.app.ui.theme.RemindMeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowTimelineScreen(navController: NavController) {
    RemindMeTheme {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        ModalBottomSheetLayout(
            sheetState = bottomSheetState, sheetElevation = 10.dp,
            sheetShape = RoundedCornerShape(
                bottomStart = 0.dp,
                bottomEnd = 0.dp,
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            sheetContent = {
                AddNewBottomSheet()
            }
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = { AppTopBar(scaffoldState, scope) },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        scope.launch {
                            if (!bottomSheetState.isVisible) {
                                bottomSheetState.show()
                            } else {
                                bottomSheetState.hide()
                            }
                        }
                    }) {
                        Icon(Icons.Filled.Add, null)
                    }
                },
                drawerContent = { NavigationDrawerContent() },
                content = { Content(navController) }
            )
        }
    }
}

@Composable
fun AppTopBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    TopAppBar(
        elevation = 5.dp,
        title = { Text("Timeline") },
        backgroundColor = MaterialTheme.colors.primary,
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
fun Content(navController: NavController) {
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
            ) {
                navController.navigate(Navigation.Routes.DETAILS)
            }

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
            ) {
                navController.navigate(Navigation.Routes.DETAILS)
            }

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
            ) {
                navController.navigate(Navigation.Routes.DETAILS)
            }

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
            ) {
                navController.navigate(Navigation.Routes.DETAILS)
            }

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
            ) {
                navController.navigate(Navigation.Routes.DETAILS)
            }

        }
    }
}

@Preview
@Composable
private fun ShowTimelineScreenPreview() {
    ShowTimelineScreen(rememberNavController())
}