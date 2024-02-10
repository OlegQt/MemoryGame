package com.memorygame.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memorygame.presentation.GameLogic
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    private val gameEngine = GameLogic()
    val getGameState get() = gameEngine.getGameState()

    var sticksQuantity: Int = 0

    var hideAllSticks: Job? = null

    init {
        newGame(4)
    }

    fun switchTheme() {
        _darkMode.value?.let {
            _darkMode.value = !it
        }
    }

    fun newGame(newRowQuantity: Int) {
        sticksQuantity = newRowQuantity
        gameEngine.startNewGame(quantity = newRowQuantity.quad())

        showAllSticks()

        // Завершить предыдущую корутину
        hideAllSticks?.cancel()

        hideAllSticks = viewModelScope.launch {
            closeAllSticks(6000L)
        }
    }

    private fun showAllSticks() {
        gameEngine.openCloseAll(true)
    }

    private suspend fun closeAllSticks(durationTime: Long) {
        var timer = 0L
        while (timer < durationTime) {
            _logLine.value = "Time $timer"
            delay(50)
            timer += 50L
        }

        //delay(durationTime)
        gameEngine.openCloseAll(false)
    }

    fun pushStick(stickId: Int) {
        viewModelScope.launch { gameEngine.push(itemPushedId = stickId) }
    }

    private fun Int.quad(): Int {
        return this.toDouble().pow(2).toInt()
    }
}