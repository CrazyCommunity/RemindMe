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

package com.remindme.app.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remindme.app.R
import com.remindme.app.navigation.Navigation
import com.remindme.app.ui.theme.RemindMeTheme

@Composable
fun ShowOTPScreen(navController: NavController) {
    RemindMeTheme {
        Surface(color = MaterialTheme.colors.primary) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = stringResource(id = R.string.login)
                )
                Spacer(modifier = Modifier.height(100.dp))
                EnterOTPInputText()
                Spacer(modifier = Modifier.height(120.dp))

                Button(
                    onClick = {
                        // TODO: remove this
                        navController.navigate(Navigation.Routes.TIMELINE)
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 48.dp,
                            end = 48.dp
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(R.string.login), style = TextStyle(Color.White)
                    )
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter)
                            .padding(bottom = 30.dp),
                        text = stringResource(R.string.privacy_policy),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun OTPPagePreview() {
    ShowOTPScreen(rememberNavController())
}

@Composable
fun EnterOTPInputText() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = {
            Text(
                stringResource(R.string.enter_otp),
                style = TextStyle(color = Color.White)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            cursorColor = Color.White,
            textColor = Color.White
        )
    )
}