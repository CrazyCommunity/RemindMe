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

package com.remindme.app.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remindme.app.ui.theme.Grey700
import com.remindme.app.ui.theme.Orange700
import com.remindme.app.ui.theme.RemindMeTheme
import java.util.*

data class ItemDetails(
    val title: String,
    val description: String = "",
    val expiry: Date? = null,
    val purchasedOn: Date? = null
)

@Composable
fun DetailsScreen(navController: NavController, itemDetails: ItemDetails? = null) {
    RemindMeTheme {
        Surface {
            Column {
                AppTopBar(itemDetails?.title ?: "")
                Content(itemDetails)
            }
        }
    }
}

@Composable
private fun AppTopBar(title: String) {
    TopAppBar(
        elevation = 5.dp,
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        actions = {
            IconButton(onClick = {
                // TODO open search screen
            }) {
                Icon(Icons.Filled.Edit, null)
            }
        }
    )
}

@Composable
private fun Content(itemDetails: ItemDetails?) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            elevation = 7.dp
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row {
                    Text(
                        text = "Expiry Date: 27-11-2022",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Orange700
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Purchase Date: 26-01-2022",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey700
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Short Notes e.g. it is kept at some place which you might forget.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            elevation = 7.dp
        ) {
            Text(
                text = "Settings",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp, top = 12.dp)
            )
            Column(modifier = Modifier.padding(12.dp)) {

            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(rememberNavController(), itemDetails = ItemDetails("Dark Soy Sauce"))
}