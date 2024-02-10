package com.memorygame.presentation

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay


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

    suspend fun push(itemPushedId: Int) {
        memoryStickList[itemPushedId] = memoryStickList[itemPushedId].copy(isOpened = true)
        delay(300)
        memoryStickList[itemPushedId] = memoryStickList[itemPushedId].copy(isOpened = false)
    }

    fun openCloseAll(mode: Boolean) {
        for (index in 0 until memoryStickList.size){
            memoryStickList[index]=memoryStickList[index].copy(isOpened = mode)
        }
    }

    fun getGameState() = memoryStickList
}