package com.memorygame.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    val darkMode = mutableStateOf(true)


    val quantity = MutableStateFlow<Int>(0)


    init {
        _logLine.value = "Hi"
    }

    fun switchDarkLightMode() {
        darkMode.value = !darkMode.value
    }

    fun plusMemory() {
        quantity.value = quantity.value + 1
    }

    fun reduceMemory() {
        quantity.value = quantity.value - 1
    }


}

class MemoryButton(
    private val id: Int = 0,
    val name: String = "BTN $id",
    val isPushed: Boolean = false
) {
    val btnState = mutableStateOf(this)
}
