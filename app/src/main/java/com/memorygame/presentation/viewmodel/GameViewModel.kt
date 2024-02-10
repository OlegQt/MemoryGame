package com.memorygame.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memorygame.presentation.GameLogic
import com.memorygame.presentation.MemoryStick
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.pow

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    private val gameEngine = GameLogic()

    private val _dataListFlow = MutableStateFlow<List<MemoryStick>>(emptyList())
    val dataListFlow: StateFlow<List<MemoryStick>> = _dataListFlow

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

        _dataListFlow.value = gameEngine.getGameState()
    }

    fun pushItem(itemPushedId: Int): Boolean {
        return gameEngine.push(itemPushedId)
    }

    private fun Int.quad(): Int {
        return this.toDouble().pow(2).toInt()
    }
}