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

import java.util.*

data class Reminder(
  val title: String,
  val notes: String,
  val purchasedOn: Date,
  val expiry: Date,
  val settings: Settings
) {

  data class Settings(val remindBefore: List<Int>, val notify: Notify)

  sealed class Notify {
    object Weekly : Notify()
    object Daily : Notify()
    object Default : Notify()
  }

}
