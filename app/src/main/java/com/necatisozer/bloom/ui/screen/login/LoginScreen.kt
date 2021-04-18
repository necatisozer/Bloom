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


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.necatisozer.bloom.R
import com.necatisozer.bloom.ui.components.BloomButton
import com.necatisozer.bloom.ui.components.BloomOutlinedTextField
import com.necatisozer.bloom.ui.screen.Screen
import com.necatisozer.bloom.utils.Constants
import com.necatisozer.bloom.utils.LocalDataManager
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
) {
    val viewState by loginViewModel.viewState.collectAsState()
    val context = LocalContext.current.applicationContext

    LaunchedEffect(loginViewModel.viewEvents) {
        loginViewModel.viewEvents.collect { event ->
            when (event) {
                LoginViewEvent.NavigateToHome -> {
                    navController.navigate(Screen.Home.route) {
                        launchSingleTop = true
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                        LocalDataManager.instance.setSharedPreferenceBoolean(context = context,Constants.HAVE_A_LOGIN,true)
                    }
                }
            }
        }
    }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.login_login_with_email),
                modifier = Modifier.paddingFromBaseline(top = 184.dp, bottom = 16.dp),
                style = MaterialTheme.typography.h1,
            )

            BloomOutlinedTextField(
                value = viewState.emailAddress,
                onValueChange = loginViewModel::onEmailAddressChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(id = R.string.login_email_address_placeholder))
                },
                isError = viewState.emailAddressError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
            )

            AnimatedVisibility(viewState.emailAddressError) {
                Text(
                    text = stringResource(id = R.string.login_invalid_email),
                    color = MaterialTheme.colors.error,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            BloomOutlinedTextField(
                value = viewState.password,
                onValueChange = loginViewModel::onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(id = R.string.login_password_placeholder))
                },
                isError = viewState.passwordError,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
            )

            AnimatedVisibility(viewState.passwordError) {
                Text(
                    text = stringResource(id = R.string.login_invalid_password),
                    color = MaterialTheme.colors.error,
                )
            }

            Text(
                text = buildAnnotatedString {
                    append("By clicking below, you agree to our ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Terms of Use")
                    }
                    append(" and consent to our ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Privacy Policy")
                    }
                    append(".")
                },
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 16.dp),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
            )

            BloomButton(
                onClick = loginViewModel::onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.login_login))
            }
        }
    }
}
