package com.memorygame.presentation

import com.memorygame.presentation.viewmodel.MemoryButton

class GameLogic {
    var buttonsQuantity = 10

    private val memoryButtonList = mutableListOf<MemoryButton>()

    fun increaseMemory() {
        buttonsQuantity++
    }


    fun fillMemory() {
        memoryButtonList.clear()

        repeat(times = buttonsQuantity) {
            memoryButtonList.add(MemoryButton(id = it))
        }
    }
}