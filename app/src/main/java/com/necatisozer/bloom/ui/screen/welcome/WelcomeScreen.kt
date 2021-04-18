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
package com.necatisozer.bloom.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import com.necatisozer.bloom.R
import com.necatisozer.bloom.ui.components.BloomButton
import com.necatisozer.bloom.ui.components.BloomTextButton
import com.necatisozer.bloom.ui.screen.Screen
import com.necatisozer.bloom.ui.theme.BloomTheme
import com.necatisozer.bloom.utils.Constants
import com.necatisozer.bloom.utils.LocalDataManager
import kotlinx.coroutines.flow.collect

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    if (LocalDataManager.instance.getSharedPreferenceBoolean(
                context = LocalContext.current.applicationContext,
                Constants.HAVE_A_LOGIN,
                false
            )
        ) {
            navController.navigate(Screen.Home.route) {
                launchSingleTop = true
                popUpTo(Screen.Welcome.route) {
                    inclusive = true
                }
            }
        }

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.welcome_bg),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(72.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 88.dp),
                painter = painterResource(id = R.drawable.welcome_illos),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.paddingFromBaseline(top = 32.dp, bottom = 40.dp),
                text = stringResource(id = R.string.welcome_subtitle),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1
            )
            BloomButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.welcome_create_account))
            }
            Spacer(modifier = Modifier.height(8.dp))
            BloomTextButton(
                onClick = {
                    navController.navigate(Screen.Login.route)
                }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    text = stringResource(id = R.string.welcome_login)
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    BloomTheme(darkTheme = false) {
        WelcomeScreen(rememberNavController())
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenDarkPreview() {
    BloomTheme(darkTheme = true) {
        WelcomeScreen(rememberNavController())
    }
}
