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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.remindme.app.R
import com.remindme.app.ui.theme.Grey500
import com.remindme.app.ui.theme.Grey700

@Composable
fun RemindMeInputView(label: String, caption: String = "", showCaption: Boolean = false) {
  var text by remember { mutableStateOf("") }
  Spacer(modifier = Modifier.size(10.dp))

  OutlinedTextField(
    value = text,
    singleLine = true,
    maxLines = 1,
    onValueChange = { text = it },
    label = {
      Text(
        label,
        color = Grey700
      )
    },
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = MaterialTheme.colors.primary,
      unfocusedBorderColor = MaterialTheme.colors.primary,
      focusedLabelColor = MaterialTheme.colors.primary,
      cursorColor = MaterialTheme.colors.primary,
      textColor = MaterialTheme.colors.primary
    ),
    modifier = Modifier.fillMaxWidth()
  )
  if (showCaption) {
    Box(
      modifier = Modifier
        .fillMaxWidth(),
      contentAlignment = Alignment.BottomEnd
    ) {
      Text(
        fontSize = 10.sp,
        fontWeight = FontWeight.Light,
        color = Grey700,
        text = caption,
        modifier = Modifier.padding(top = 5.dp)
      )
    }
  }
}

@Composable
fun DatePickerView(label: String) {
  var text by remember { mutableStateOf("") }
  Spacer(modifier = Modifier.size(10.dp))

  val trailingIconView = @Composable {
    IconButton(
      onClick = {
        // TODO open default data picker
      },
    ) {
      Image(
        painter = painterResource(id = R.drawable.ic_calendar),
        null
      )
    }
  }

  OutlinedTextField(
    value = text,
    onValueChange = { text = it },
    singleLine = true,
    maxLines = 1,
    readOnly = true,
    label = {
      Row {
        Text(label, color = Grey700)
        Box(
          modifier = Modifier
            .fillMaxWidth(),
          contentAlignment = Alignment.BottomEnd
        ) { Text("03-07-2022", color = Grey700) }
      }
    },
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = MaterialTheme.colors.primary,
      unfocusedBorderColor = MaterialTheme.colors.primary,
      focusedLabelColor = MaterialTheme.colors.primary,
      cursorColor = MaterialTheme.colors.primary,
      textColor = MaterialTheme.colors.primary
    ),
    modifier = Modifier.fillMaxWidth(),
    trailingIcon = trailingIconView
  )
}

@Composable
fun RemindBeforeCheckboxGroup() {
  Column(modifier = Modifier.padding(top = 20.dp)) {
    Row {
      CheckboxWithLabel(label = "60 days")
      Spacer(Modifier.size(width = 50.dp, height = 0.dp))
      CheckboxWithLabel(label = "7 days")
    }
    Row {
      CheckboxWithLabel(label = "30 days")
      Spacer(Modifier.size(width = 50.dp, height = 0.dp))
      CheckboxWithLabel(label = "1 day")
    }
  }
}

@Composable
fun CheckboxWithLabel(label: String) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .clip(MaterialTheme.shapes.small)
      .requiredHeight(ButtonDefaults.MinHeight)
      .padding(4.dp)
  ) {
    val isChecked = remember { mutableStateOf(false) }
    Checkbox(
      checked = isChecked.value,
      onCheckedChange = { isChecked.value = it },
      enabled = true,
      colors = CheckboxDefaults.colors(
        checkedColor = MaterialTheme.colors.primary,
        uncheckedColor = Grey500
      )
    )
    Text(text = label, color = Grey700)
  }
}

@Preview
@Composable
fun CheckboxGroupPreview() {
  RemindBeforeCheckboxGroup()
}