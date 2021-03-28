package com.necatisozer.bloom.ui.screen

import androidx.compose.runtime.Immutable

sealed class Screen(val route: String) {
    @Immutable
    object Welcome : Screen("welcome")

    @Immutable
    object Login : Screen("login")

    @Immutable
    object Home : Screen("home")

    @Immutable
    object Favorites : Screen("favorites")

    @Immutable
    object Profile : Screen("profile")

    @Immutable
    object Cart : Screen("cart")
}

