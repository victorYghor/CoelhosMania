package com.example.restapi

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapi.data.Rabbit
import com.example.restapi.data.RabbitsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: RabbitsAPI
): ViewModel() {

    private val _state = mutableStateOf(RabbitState())
    val state: State<RabbitState> = _state

    init {
        getRandomRabbit()
    }

    fun getRandomRabbit() {
        try {
            _state.value = state.value.copy(isLoading = true)
            Log.i("Everything is ok", "success collecting info in the internet")
            viewModelScope.launch {
                _state.value = state.value.copy(
                    isLoading = false,
                    rabbit = api.getRandomRabbit()
                )
            }
        } catch(e: Exception) {
            Log.e("MainViewModel", "getRandomRabbit: ", e)
            _state.value = state.value.copy(isLoading = false)
        }
        viewModelScope.launch {
            api.getRandomRabbit()
        }
    }
    data class RabbitState(
        val rabbit: Rabbit? = null,
        val isLoading: Boolean = false
    )
}