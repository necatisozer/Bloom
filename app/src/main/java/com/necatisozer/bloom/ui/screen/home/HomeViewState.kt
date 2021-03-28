package com.necatisozer.bloom.ui.screen.home

import com.necatisozer.bloom.data.Checkable
import com.necatisozer.bloom.data.Plant
import com.necatisozer.bloom.data.Theme

data class HomeViewState(
    val searchText: String = "",
    val themes: List<Theme>? = null,
    val plants: List<Checkable<Plant>>? = null,
)
