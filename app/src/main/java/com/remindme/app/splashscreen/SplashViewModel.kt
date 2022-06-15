package com.remindme.app.splashscreen

import androidx.lifecycle.viewModelScope
import com.remindme.app.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashViewState, SplashViewEvent, SplashViewAction>() {
  override val initialViewState: SplashViewState
    get() = SplashViewState.Default

  override fun processAction(action: SplashViewAction) {
    when (action) {
      is SplashViewAction.Launch -> startTimer()
    }
  }

  private fun startTimer() {
    viewModelScope.launch {
      delay(3000)
      updateViewState(SplashViewState.Timeout)
    }
  }

}