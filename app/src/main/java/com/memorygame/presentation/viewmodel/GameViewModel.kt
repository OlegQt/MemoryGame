package com.memorygame.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.memorygame.presentation.MemoryStick
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _logLine = MutableLiveData<String>("initial\n")
    val logLine = _logLine as LiveData<String>

    private val _darkMode = MutableLiveData<Boolean>(true)
    val darkMode = _darkMode as LiveData<Boolean>

    private val _rowQuantity = MutableLiveData<Int>(null)
    val rowQuantity = _rowQuantity as LiveData<Int>

    private val memoryList: MutableList<MutableStateFlow<MemoryStick>> = mutableListOf()


    init {
        _logLine.value = "Hi"
        newGame(4)
    }

    fun switchTheme() {
        _darkMode.value?.let {
            _darkMode.value = !it
        }
    }

    fun newGame(newRowQuantity: Int) {
        memoryList.clear()

        repeat(times = newRowQuantity * newRowQuantity) {
            memoryList.add(
                MutableStateFlow(MemoryStick(id = it, name = "S$it"))
            )
        }

        _rowQuantity.value = newRowQuantity
    }

    fun pushItem(itemPushedId: Int) {
        val newStick = memoryList.elementAt(index = itemPushedId)

        newStick.value = newStick.value.copy(name = "F")

        viewModelScope.launch {
            delay(200)
            newStick.value = newStick.value.copy(name = "S")
        }
    }

    fun getStick(id: Int): MutableStateFlow<MemoryStick>? {
        return if (memoryList.size < id) null
        else memoryList.elementAt(index = id)
    }

}