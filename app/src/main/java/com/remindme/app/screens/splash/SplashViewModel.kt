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

package com.remindme.app.screens.splash

import androidx.lifecycle.viewModelScope
import com.remindme.app.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashViewState, SplashViewEvent, SplashViewAction>() {
  override val initialViewState: SplashViewState
    get() = SplashViewState.Default

  override fun processAction(action: SplashViewAction) {
    when (action) {
      is SplashViewAction.Launch -> fetchRequiredData()
    }
  }

  private fun fetchRequiredData() {
    viewModelScope.launch {
      // execute API call or process requirements for loading next screen
      delay(3000) // todo remove once splash screen API implemented
      updateViewState(SplashViewState.LoadingComplete)
    }
  }

}