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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.remindme.app.ui.component.ReminderItemView
import com.remindme.app.ui.component.RevealDirection
import com.remindme.app.ui.component.RevealSwipe
import com.remindme.app.ui.component.SwipeRevealView
import com.remindme.app.ui.theme.Green700
import com.remindme.app.ui.theme.Orange700
import com.remindme.app.ui.theme.Red700
import com.remindme.app.ui.theme.RemindMeTheme
import com.remindme.app.ui.theme.Yellow700
import com.remindme.app.ui.theme.dashedBorder
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimelineListGroupView(list: List<Reminder>, timelineState: TimelineState) {
  RemindMeTheme {
    Surface {
      ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (spaceFirst, spaceSecond, startIndicator, endIndicator, headerText, indicatorLine, indicatorLineTop, listGroup, textEnd) = createRefs()

        Spacer(modifier = Modifier
          .size(20.dp)
          .constrainAs(spaceFirst) {})

        Box(
          modifier = Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(timelineState.color)
            .constrainAs(startIndicator) {
              start.linkTo(spaceFirst.end)
              top.linkTo(headerText.top)
              bottom.linkTo(headerText.bottom)
            }
        )

        Spacer(modifier = Modifier
          .size(25.dp)
          .constrainAs(spaceSecond) {})

        Divider(
          color = Color.Transparent,
          modifier = Modifier
            .fillMaxHeight()
            .width(2.dp)
            .dashedBorder(
              width = 2.dp,
              color = timelineState.color,
              shape = MaterialTheme.shapes.medium, on = 4.dp, off = 4.dp
            )
            .constrainAs(indicatorLine) {
              start.linkTo(spaceSecond.end)
              top.linkTo(startIndicator.bottom)
              bottom.linkTo(if (timelineState == TimelineState.LATER) endIndicator.top else listGroup.bottom)
              height = Dimension.fillToConstraints
            }
        )

        val dashColor = when (timelineState) {
          TimelineState.TOMORROW -> TimelineState.TODAY.color
          TimelineState.THIS_WEEK -> TimelineState.TOMORROW.color
          TimelineState.LATER -> TimelineState.THIS_WEEK.color
          else -> TimelineState.EXPIRED.color
        }

        Divider(
          color = Color.Transparent,
          modifier = Modifier
            .fillMaxHeight()
            .width(2.dp)
            .alpha(if (timelineState == TimelineState.EXPIRED) 0f else 1f)
            .dashedBorder(
              width = 2.dp,
              color = dashColor,
              shape = MaterialTheme.shapes.medium, on = 4.dp, off = 4.dp
            )
            .constrainAs(indicatorLineTop) {
              start.linkTo(spaceSecond.end)
              top.linkTo(parent.top)
              bottom.linkTo(startIndicator.top)
              height = Dimension.fillToConstraints
            }
        )

        Text(
          text = timelineState.text,
          fontSize = 20.sp,
          fontWeight = FontWeight.Medium,
          color = timelineState.color,
          modifier = Modifier
            .padding(start = 10.dp)
            .constrainAs(headerText) {
              start.linkTo(startIndicator.end)
              start.linkTo(startIndicator.end)
            }
        )

        Column(modifier = Modifier
          .padding(10.dp)
          .constrainAs(listGroup) {
            top.linkTo(headerText.bottom)
            start.linkTo(headerText.start)
            end.linkTo(parent.end)
            if (timelineState == TimelineState.LATER) bottom.linkTo(textEnd.top)
            width = Dimension.fillToConstraints
          }) {
          list.forEach { item ->
            RevealSwipe(
              directions = setOf(
                RevealDirection.End
              ),
              onContentClick = {
                println("hello")
              },
              hiddenContentEnd = {
                SwipeRevealView(onEdit = { /*TODO*/ }) {
                }
              }
            ) {
              ReminderItemView(item)
            }
          }
        }

        Box(
          modifier = Modifier
            .size(12.dp)
            .clip(CircleShape)
            .alpha(if (timelineState == TimelineState.LATER) 1f else 0f)
            .background(timelineState.color)
            .constrainAs(endIndicator) {
              start.linkTo(spaceFirst.end)
              top.linkTo(textEnd.top)
              bottom.linkTo(textEnd.bottom)
            }
        )

        Text(
          text = "That's it!",
          fontSize = 20.sp,
          fontWeight = FontWeight.Medium,
          color = Green700,
          modifier = Modifier
            .alpha(if (timelineState == TimelineState.LATER) 1f else 0f)
            .padding(start = 10.dp, top = 20.dp, bottom = 30.dp)
            .constrainAs(textEnd) {
              start.linkTo(headerText.start)
              bottom.linkTo(parent.bottom)
            }
        )
      }
    }
  }
}

@Composable
@Preview
private fun TimelineListGroupViewPreview() {
  TimelineListGroupView(
    listOf(
      Reminder(
        "Dark Soy Sauce", "Short notes to be added here...", Date(), Date(), Reminder.Settings(
          emptyList(), Reminder.Notify.Default
        )
      )
    ), TimelineState.EXPIRED
  )
}

enum class TimelineState(val text: String, val color: Color) {
  EXPIRED("Expired", Red700),
  TODAY("Today", Red700),
  TOMORROW("Tomorrow", Orange700),
  THIS_WEEK("This Week", Yellow700),
  LATER("Later", Green700)
}