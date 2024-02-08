package com.memorygame.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memorygame.presentation.GameLogic

import com.memorygame.presentation.MemoryStick
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    private val _memoryCount = MutableLiveData<Int>(null)
    val memoryCount = _memoryCount as LiveData<Int>

    private val memoryList: MutableList<MutableStateFlow<MemoryStick>> = mutableListOf()


    init {
        _logLine.value = "Hi"
        newGame(10)
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
        val newStick = memoryList.elementAt(index = itemPushedId)

        newStick.value = newStick.value.copy(name = "F")

        viewModelScope.launch {
            delay(200)
            newStick.value = newStick.value.copy(name = "Stick")
        }
    }

    fun getStick(id: Int): MutableStateFlow<MemoryStick> {
        if (memoryList.isEmpty()) return MutableStateFlow(MemoryStick(id = 0, name = "Default"))
        return memoryList.elementAt(index = id)
    }


}