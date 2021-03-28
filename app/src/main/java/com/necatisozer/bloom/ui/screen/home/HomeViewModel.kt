package com.necatisozer.bloom.ui.screen.home

import androidx.lifecycle.ViewModel
import com.necatisozer.bloom.data.Checkable
import com.necatisozer.bloom.data.MockData
import com.necatisozer.bloom.data.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState> = _viewState.asStateFlow()

    init {
        _viewState.value = _viewState.value.copy(
            themes = MockData.themes,
            plants = MockData.plants.map { Checkable(it) },
        )
    }

    fun onSearchTextChange(value: String) {
        _viewState.value = _viewState.value.copy(
            searchText = value
        )
    }

    fun onPlantCheckedChange(plant: Plant, checked: Boolean) {
        val plants = _viewState.value.plants?.map { checkablePlant ->
            if (checkablePlant.data.name == plant.name) {
                checkablePlant.copy(checked = checked)
            } else {
                checkablePlant
            }
        }

        _viewState.value = _viewState.value.copy(
            plants = plants
        )
    }
}