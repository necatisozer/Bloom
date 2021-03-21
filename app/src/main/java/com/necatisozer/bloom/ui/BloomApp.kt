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
package com.necatisozer.bloom.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.necatisozer.bloom.ui.screen.WELCOME_SCREEN
import com.necatisozer.bloom.ui.screen.WelcomeScreen
import com.necatisozer.bloom.ui.screen.login.LOGIN_SCREEN
import com.necatisozer.bloom.ui.screen.login.LoginScreen
import com.necatisozer.bloom.ui.theme.BloomTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun BloomApp() {
    BloomTheme {
        ProvideWindowInsets {
            val navController = rememberNavController()

            NavHost(navController, startDestination = WELCOME_SCREEN) {
                composable(WELCOME_SCREEN) { WelcomeScreen(navController) }
                composable(LOGIN_SCREEN) { LoginScreen(navController) }
            }
        }
    }
}
