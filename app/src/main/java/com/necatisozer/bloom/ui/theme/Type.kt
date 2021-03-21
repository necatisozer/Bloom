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

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.necatisozer.bloom.R

val FontFamily.Companion.NunitoSans
    get() = FontFamily(
        Font(R.font.nunito_sans_light, FontWeight.Light),
        Font(R.font.nunito_sans_semibold, FontWeight.SemiBold),
        Font(R.font.nunito_sans_bold, FontWeight.Bold)
    )

val BloomTheme.typography
    get() = Typography(
        defaultFontFamily = FontFamily.NunitoSans,
        h1 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            letterSpacing = 0.sp,
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            letterSpacing = .15.sp,
        ),
        subtitle1 = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.sp,
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
        ),
        button = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            letterSpacing = 1.sp,
        ),
        caption = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
        ),
    )
