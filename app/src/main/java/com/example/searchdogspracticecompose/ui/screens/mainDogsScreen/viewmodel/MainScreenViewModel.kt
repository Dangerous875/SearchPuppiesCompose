package com.example.searchdogspracticecompose.ui.screens.mainDogsScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchdogspracticecompose.domain.GetAllBreedsUseCase
import com.example.searchdogspracticecompose.domain.GetDogsByBreedsUseCase
import com.example.searchdogspracticecompose.domain.GetSelectBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getDogsByBreedsUseCase: GetDogsByBreedsUseCase,
    private val getSelectBreed: GetSelectBreed,
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) :
    ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _allBreeds = MutableStateFlow<List<String>>(emptyList())
    val allBreeds = _allBreeds.asStateFlow()
    private val _listDogs = MutableStateFlow<List<String>>(emptyList())
    val lisDogs = _listDogs.asStateFlow()

    init {
        _allBreeds.value = getAllBreedsUseCase()
        _isLoading.value = true
        viewModelScope.launch {
            _listDogs.value = getDogsByBreedsUseCase(getSelectBreed())
            _isLoading.value = false
        }
    }

    fun getDogsByBreeds(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            _listDogs.value = getDogsByBreedsUseCase(query)
            delay(2000)
            _isLoading.value = false
        }
    }
}