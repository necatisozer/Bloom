package com.necatisozer.bloom.ui.screen.login

sealed class LoginViewEvent {
    object NavigateToHome : LoginViewEvent()
}
