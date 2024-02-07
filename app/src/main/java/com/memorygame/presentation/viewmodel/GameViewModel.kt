package com.memorygame.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memorygame.presentation.GameLogic
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    private val gameEngine = GameLogic()
    val gameState = gameEngine.gameState

    init {
        _logLine.value = "Hi"
        gameEngine.changeSticksQuantity(4)
    }

    fun switchTheme() {
        _darkMode.value?.let {
            _darkMode.value = !it
        }
    }

    fun updateDynamic(){
        gameEngine.changeSticksQuantity(Random.nextInt(10))
    }

    fun remake(){
        gameEngine.update()
    }
}