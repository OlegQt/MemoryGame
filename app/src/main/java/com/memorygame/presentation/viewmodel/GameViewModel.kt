package com.memorygame.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memorygame.presentation.GameLogic

import com.memorygame.presentation.MemoryStick
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    private val _memoryCount = MutableLiveData<Int>(10)
    val memoryCount = _memoryCount as LiveData<Int>

    private val memoryList: MutableList<MutableStateFlow<MemoryStick>> = mutableListOf()


    init {
        _logLine.value = "Hi"
    }

    fun switchTheme() {
        _darkMode.value?.let {
            _darkMode.value = !it
        }
    }

    fun newGame(memoryStickQuantity: Int) {
        memoryList.clear()

        repeat(times = memoryStickQuantity) {
            memoryList.add(
                MutableStateFlow(MemoryStick(id = it, name = "stick $it"))
            )
        }

        _memoryCount.value = memoryStickQuantity
    }

    fun pushItem(itemPushedId: Int) {
        val newStick = memoryList.elementAt(index = itemPushedId).value.copy(name = "Pushed")

        memoryList.elementAt(index = itemPushedId).value = newStick

    }

    fun getStick(id: Int): MutableStateFlow<MemoryStick> {
        if (memoryList.isEmpty()) return MutableStateFlow(MemoryStick(id = 0, name = "Default"))
        return memoryList.elementAt(index = id)
    }

}