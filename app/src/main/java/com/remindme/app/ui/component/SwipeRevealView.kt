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

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.remindme.app.R
import com.remindme.app.ui.theme.Blue700
import com.remindme.app.ui.theme.Red700

@Composable
fun SwipeRevealView(
  onEdit: () -> Unit,
  onDelete: () -> Unit
) {
  Row {
    IconButton(
      onClick = onEdit,
      content = {
        Icon(
          painter = painterResource(id = R.drawable.ic_edit),
          tint = Blue700,
          contentDescription = null
        )
      }
    )
    IconButton(
      onClick = onDelete,
      content = {
        Icon(
          painter = painterResource(id = R.drawable.ic_delete),
          tint = Red700,
          contentDescription = null
        )
      }
    )
  }
}

@Composable
@Preview
fun SwipeRevealViewPreview() {
  SwipeRevealView({}, {})
}