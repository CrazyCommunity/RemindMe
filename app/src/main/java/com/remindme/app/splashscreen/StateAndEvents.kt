package com.remindme.app.splashscreen

sealed class SplashViewState {
  object Default : SplashViewState()
  object Timeout: SplashViewState()
}

sealed class SplashViewEvent {
  data class Message(val message: String) : SplashViewEvent()
}

sealed class SplashViewAction {
  object Launch : SplashViewAction()
}