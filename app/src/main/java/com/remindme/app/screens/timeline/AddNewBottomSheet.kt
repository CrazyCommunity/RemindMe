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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.remindme.app.R
import com.remindme.app.ui.component.RemindBeforeCheckboxGroup
import com.remindme.app.ui.component.DatePickerView
import com.remindme.app.ui.component.RemindMeInputView
import com.remindme.app.ui.theme.Grey700

@Composable
fun AddNewBottomSheet() {
  Surface {
    Column(
      modifier = Modifier
        .padding(16.dp)
    ) {
      Text(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        text = stringResource(id = R.string.add_new),
        color = Grey700
      )
      RemindMeInputView(stringResource(id = R.string.title))
      RemindMeInputView(
        stringResource(id = R.string.notes),
        stringResource(id = R.string.txt_max_chars),
        true
      )
      DatePickerView(stringResource(id = R.string.purchased_on))
      DatePickerView(stringResource(id = R.string.expiry))
      Text(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        text = stringResource(id = R.string.remind_me_before),
        color = Grey700,
        modifier = Modifier.padding(top = 20.dp)
      )
      RemindBeforeCheckboxGroup()
      Button(
        onClick = {
          // TODO: remove this
        },
        Modifier
          .fillMaxWidth()
          .padding(start = 12.dp, top = 20.dp, end = 12.dp),
        colors = ButtonDefaults.buttonColors(
          backgroundColor = MaterialTheme.colors.primary,
          contentColor = MaterialTheme.colors.primary
        )
      ) {
        Text(
          text = stringResource(R.string.add), style = TextStyle(Color.White)
        )
      }
    }
  }
}

@Preview
@Composable
fun AddNewBottomSheetPreview() {
  AddNewBottomSheet()
}