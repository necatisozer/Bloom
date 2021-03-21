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
package com.necatisozer.bloom.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object BloomTheme {
    val lightColors = lightColors(
        primary = Color.Pink100,
        secondary = Color.Pink900,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.Gray900,
        onSecondary = Color.White,
        onBackground = Color.Gray900,
        onSurface = Color.Gray900,
    )

    val darkColors = darkColors(
        primary = Color.Green900,
        secondary = Color.Green300,
        background = Color.Gray900,
        surface = Color.White.copy(alpha = .15f),
        onPrimary = Color.White,
        onSecondary = Color.Gray900,
        onBackground = Color.White,
        onSurface = Color.White.copy(alpha = .85f),
    )
}

@Composable
fun BloomTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) BloomTheme.darkColors else BloomTheme.lightColors,
        typography = BloomTheme.typography,
        shapes = BloomTheme.shapes,
        content = content,
    )
}
