package com.example.searchdogspracticecompose.ui.theme.screens.mainDogsScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchdogspracticecompose.domain.GetDogsByBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getDogsByBreedsUseCase: GetDogsByBreedsUseCase) :
    ViewModel() {
 // _isLoading _listDogs lisDogs isLoading



    fun getDogsByBreeds(query: String) {
        Log.i("cris",lisDogs.value.toString())
        _isLoading.value = true
        viewModelScope.launch {
            _listDogs.value = getDogsByBreedsUseCase(query)
            delay(2000)
            _isLoading.value = false
            Log.i("cris",lisDogs.value.toString())
        }
    }
}