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

package com.remindme.app.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.remindme.app.screens.timeline.Reminder
import com.remindme.app.ui.theme.Grey700
import com.remindme.app.ui.theme.Orange700
import com.remindme.app.ui.theme.RemindMeTheme

@Composable
fun ReminderItemView(reminder: Reminder? = null) {
  RemindMeTheme {
    Surface {
      Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp,
        modifier = Modifier
          .fillMaxWidth()
          .padding(5.dp)
      ) {
        ConstraintLayout(
          modifier = Modifier
            .padding(10.dp)
        ) {
          val (title, notes, space, purchaseDate, expiredWhenText) = createRefs()
          Text(
            text = reminder?.title ?: "Title",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Grey700,
            modifier = Modifier
              .constrainAs(title) {}
          )

          Text(
            text = reminder?.notes ?: "Short Notes",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Grey700,
            modifier = Modifier
              .constrainAs(notes) {
                top.linkTo(title.bottom)
              }
          )

          Spacer(modifier = Modifier
            .size(4.dp)
            .constrainAs(space) {
              top.linkTo(notes.bottom)
            })

          Text(
            text = "Purchase date: 20-02-2022",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Grey700,
            modifier = Modifier
              .constrainAs(purchaseDate) {
                top.linkTo(space.bottom)
              }
          )

          Text(
            text = "03-05-2022",
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = Orange700,
            modifier = Modifier
              .constrainAs(expiredWhenText) {
                end.linkTo(parent.end)
              }
          )

        }
      }
    }
  }
}

@Composable
@Preview
private fun ReminderItemViewPreview() {
  ReminderItemView()
}