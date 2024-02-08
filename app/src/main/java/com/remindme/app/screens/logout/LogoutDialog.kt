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

package com.remindme.app.screens.logout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.remindme.app.R


@Composable
fun SetupLogoutDialog() {
    // State to manage if the alert dialog is showing or not.
    // Default is false (not showing)
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    Column(
        // Make the column fill the whole screen space (width and height).
        modifier = Modifier.fillMaxSize(),
        // Place all children at center horizontally.
        horizontalAlignment = Alignment.CenterHorizontally,
        // Place all children at center vertically.
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                setShowDialog(true)
            }) {
            Text("Show Dialog")
        }
        // Create alert dialog, pass the showDialog state to this Composable
        LogoutDialog(showDialog, setShowDialog)
    }
}


@Composable
fun LogoutDialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text(
                        stringResource(R.string.yes),
                        color = colorResource(id = R.color.dialog_text_color)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text(
                        stringResource(R.string.no),
                        color = colorResource(id = R.color.dialog_text_color)
                    )
                }
            },
            text = {
                Text(
                    stringResource(R.string.logout_message),
                    style = MaterialTheme.typography.h6,
                    color = colorResource(id = R.color.dialog_text_color)
                )
            }
        )
    }
}