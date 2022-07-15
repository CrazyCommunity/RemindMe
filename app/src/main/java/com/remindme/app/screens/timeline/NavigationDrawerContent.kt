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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.remindme.app.R
import com.remindme.app.screens.logout.LogoutDialog
import com.remindme.app.ui.theme.Grey700

@Composable
fun NavigationDrawerContent() {
    Surface {
        val (showDialog, setShowDialog) = remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.fillMaxSize()) {
            NavigationDrawerHeader()
            Spacer(modifier = Modifier.size(25.dp))
            DrawerBody(items = listOf(
                MenuItem(
                    type = MenuType.Timeline,
                    title = stringResource(id = R.string.menu_timeline),
                    contentDescription = stringResource(id = R.string.menu_timeline),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_timeline)
                ),
                MenuItem(
                    type = MenuType.Settings,
                    title = stringResource(id = R.string.menu_settings),
                    contentDescription = stringResource(id = R.string.timeline),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_settings)
                ),
                MenuItem(
                    type = MenuType.Logout,
                    title = stringResource(id = R.string.menu_logout),
                    contentDescription = stringResource(id = R.string.menu_logout),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_logout)
                ),
                MenuItem(
                    type = MenuType.TermsOfUse,
                    title = stringResource(id = R.string.menu_terms_of_use),
                    contentDescription = stringResource(id = R.string.menu_terms_of_use),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_terms)
                ),
                MenuItem(
                    type = MenuType.PrivacyPolicy,
                    title = stringResource(id = R.string.menu_privacy_policy),
                    contentDescription = stringResource(id = R.string.menu_privacy_policy),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_privacy)
                ),
                MenuItem(
                    type = MenuType.EmailUs,
                    title = stringResource(id = R.string.menu_email_us),
                    contentDescription = stringResource(id = R.string.menu_email_us),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_email)
                )
            ), onItemClick = {
                when (it.type) {
                    MenuType.Timeline -> {
                        // Todo: show timeline page
                    }
                    MenuType.Settings -> {
                        // Todo: show setting page
                    }
                    MenuType.Logout -> {
                        setShowDialog(true)
                    }
                    MenuType.TermsOfUse -> {
                        // Todo: show Term of use page
                    }
                    MenuType.PrivacyPolicy -> {
                        // Todo: show privacy policy
                    }
                    MenuType.EmailUs -> {
                        // Todo: show email us page
                    }
                }
            })

            Box(
                modifier = Modifier.fillMaxSize(),
                Alignment.BottomStart
            ) {
                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Grey700,
                    text = "version: 1.3",
                    modifier = Modifier.padding(16.dp)
                )
            }
            LogoutDialog(showDialog, setShowDialog)
        }
    }
}

@Composable
fun NavigationDrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Application Icon"
        )
        Text(
            text = "user.email@provider.com",
            modifier = Modifier.padding(start = 20.dp, bottom = 20.dp),
            color = Color.White
        )
    }
}

@Preview
@Composable
fun LeftDrawerContentPreview() {
    NavigationDrawerContent()
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = Grey700
    ),
    onItemClick: (MenuItem) -> Unit
) {

    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                    .clickable {
                        onItemClick(item)
                    },
            ) {
                Image(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, style = textStyle, modifier = Modifier.weight(1f))
            }
        }
    }
}