package com.memorygame.presentation

import androidx.compose.runtime.mutableStateListOf


class GameLogic() {

    private var quantityOfSticks = 0
    private val memoryStickList = mutableStateListOf<MemoryStick>()

    fun startNewGame(quantity: Int) {
        quantityOfSticks = quantity
        memoryStickList.clear()

        repeat(quantity) {
            memoryStickList.add(MemoryStick(id = it))
        }
    }

    fun push(itemPushedId: Int): Boolean {
        return memoryStickList.elementAt(index = itemPushedId).apply {
            isOpened = !isOpened
        }.isOpened
    }

    fun openCloseAll(mode: Boolean) {
        memoryStickList.forEach {
            it.isOpened = mode

            if (!mode) it.name = "D"
        }
        memoryStickList.add(MemoryStick())
    }

    fun getGameState() = memoryStickList
}