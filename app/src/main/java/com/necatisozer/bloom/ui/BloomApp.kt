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

import CartScreen
import FavoritesScreen
import HomeScreen
import ProfileScreen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.necatisozer.bloom.R
import com.necatisozer.bloom.ui.screen.Screen
import com.necatisozer.bloom.ui.screen.bottomnav.BottomNavModel
import com.necatisozer.bloom.ui.screen.login.LoginScreen
import com.necatisozer.bloom.ui.screen.welcome.WelcomeScreen
import com.necatisozer.bloom.ui.theme.BloomTheme
import com.necatisozer.bloom.utils.Constants
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
fun BloomApp() {
    Constants.CURRENT_CONTEXT = LocalContext.current.applicationContext
    BloomTheme {
        ProvideWindowInsets {
            val navController = rememberNavController()

            val bottomNavItems = listOf(
                BottomNavModel(Icons.Filled.Home, R.string.bottom_nav_home, Screen.Home),
                BottomNavModel(
                    Icons.Filled.FavoriteBorder,
                    R.string.bottom_nav_favorites,
                    Screen.Favorites
                ),
                BottomNavModel(
                    Icons.Filled.AccountCircle,
                    R.string.bottom_nav_profile,
                    Screen.Profile
                ),
                BottomNavModel(Icons.Filled.ShoppingCart, R.string.bottom_nav_cart, Screen.Cart),
            )

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

            Scaffold(
                modifier = Modifier.navigationBarsPadding(),
                bottomBar = {
                    bottomNavItems.forEach { bottomNavModel ->
                        if (currentRoute == bottomNavModel.screen.route) {
                            BottomNavigation(
                                backgroundColor = MaterialTheme.colors.primary
                            ) {
                                bottomNavItems.forEach { bottomNavItem ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                imageVector = bottomNavItem.icon,
                                                contentDescription = stringResource(id = bottomNavItem.labelResourceId),
                                            )
                                        },
                                        label = { Text(stringResource(id = bottomNavItem.labelResourceId)) },
                                        selected = currentRoute == bottomNavItem.screen.route,
                                        onClick = {
                                            navController.navigate(bottomNavItem.screen.route) {
                                                // Pop up to the start destination of the graph to
                                                // avoid building up a large stack of destinations
                                                // on the back stack as users select items
                                                popUpTo(Screen.Home.route) {}
                                                // Avoid multiple copies of the same destination when
                                                // reselecting the same item
                                                launchSingleTop = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            ) {
                NavHost(navController, startDestination = Screen.Welcome.route) {
                    composable(Screen.Welcome.route) { WelcomeScreen(navController) }
                    composable(Screen.Login.route) { LoginScreen(navController) }
                    composable(Screen.Home.route) { HomeScreen(navController) }
                    composable(Screen.Favorites.route) { FavoritesScreen(navController) }
                    composable(Screen.Profile.route) { ProfileScreen(navController) }
                    composable(Screen.Cart.route) { CartScreen(navController) }
                }
            }
        }
    }
}
