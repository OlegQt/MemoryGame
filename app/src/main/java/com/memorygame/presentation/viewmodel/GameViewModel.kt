package com.memorygame.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    init {
        _logLine.value = "Hi"
    }

    fun switchTheme() {
        _darkMode.value?.let {
            _darkMode.value = !it
        }
    }
}