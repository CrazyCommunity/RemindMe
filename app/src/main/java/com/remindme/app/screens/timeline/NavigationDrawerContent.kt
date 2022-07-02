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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.remindme.app.R
import com.remindme.app.ui.theme.Grey700
import com.remindme.app.ui.theme.Teal100

@Composable
fun NavigationDrawerContent() {
  Surface {
    Column(modifier = Modifier.fillMaxSize()) {
      NavigationDrawerHeader()
      Spacer(modifier = Modifier.size(25.dp))
      listOf(
        NavDrawerItem.Timeline,
        NavDrawerItem.Settings,
        NavDrawerItem.Logout,
        NavDrawerItem.TermsOfUse,
        NavDrawerItem.PrivacyPolicy,
        NavDrawerItem.EmailUs
      ).forEach {
        DrawerMenuItem(it, it.route == NavDrawerItem.Timeline.route)
      }

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

@Composable
fun DrawerMenuItem(item: NavDrawerItem, selected: Boolean = false) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(if (selected) Teal100 else Color.Transparent),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Image(
      painter = painterResource(id = item.icon),
      null,
      modifier = Modifier.padding(start = 30.dp, top = 15.dp, bottom = 15.dp)
    )
    Text(
      text = item.title,
      fontSize = 18.sp,
      fontWeight = FontWeight.Medium,
      color = Grey700,
      modifier = Modifier.padding(start = 15.dp)
    )
  }
}

@Preview
@Composable
fun LeftDrawerContentPreview() {
  NavigationDrawerContent()
}