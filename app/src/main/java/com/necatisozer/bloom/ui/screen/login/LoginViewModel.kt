/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.necatisozer.bloom.ui.screen.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class LoginViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState> = _viewState.asStateFlow()

    private val _viewEvents = Channel<LoginViewEvent>()
    val viewEvents: Flow<LoginViewEvent> = _viewEvents.receiveAsFlow()

    fun onEmailAddressChange(value: String) {
        _viewState.value = _viewState.value.copy(
            emailAddress = value,
            emailAddressError = false,
        )
    }

    fun onPasswordChange(value: String) {
        _viewState.value = _viewState.value.copy(
            password = value,
            passwordError = false,
        )
    }

    fun onLoginClick() {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()

        if (isEmailValid && isPasswordValid) {
            _viewEvents.offer(LoginViewEvent.NavigateToHome)
        }
    }

    private fun validateEmail(): Boolean {
        val email = viewState.value.emailAddress
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (isEmailValid.not()) {
            _viewState.value = _viewState.value.copy(
                emailAddressError = true,
            )
        }

        return isEmailValid
    }

    private fun validatePassword(): Boolean {
        val password = viewState.value.password
        val isPasswordValid = password.length >= 8

        if (isPasswordValid.not()) {
            _viewState.value = _viewState.value.copy(
                passwordError = true,
            )
        }

        return isPasswordValid
    }
}
