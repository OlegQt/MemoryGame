package com.memorygame.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memorygame.presentation.GameLogic
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    private val gameEngine = GameLogic()

    val list = mutableListOf<String>("A","C","Q")

    init {
        newGame(4)
    }

    fun switchTheme() {
        _darkMode.value?.let {
            _darkMode.value = !it
        }
    }

    fun newGame(newRowQuantity: Int) {
        gameEngine.startNewGame(quantity = newRowQuantity.quad())

        viewModelScope.launch { openAllSticks(2000L) }

    }

    private suspend fun openAllSticks(durationTime: Long) {
        gameEngine.openCloseAll(mode = true)

        delay(durationTime)

        gameEngine.openCloseAll(mode = false)

    }

    private fun Int.quad(): Int {
        return this.toDouble().pow(2).toInt()
    }

    fun getGameState() = gameEngine.getGameState()
}