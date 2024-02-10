package com.memorygame.presentation

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay
import kotlin.random.Random


class GameLogic() {
    private var quantityOfSticks = 0
    private var currentSelectedStickId: Int? = null

    private val memoryStickList = mutableStateListOf<MemoryStick>()
    fun getGameState() = memoryStickList

    fun startNewGame(quantity: Int) {
        quantityOfSticks = quantity
        memoryStickList.clear()

        repeat(quantity) {
            memoryStickList.add(
                MemoryStick(
                    id = it
                )
            )
        }

        // Заполнение иконками
        var index = 0
        while (index < quantity-1) {
            val iconIndex = Random.nextInt(StickIcon.values().size - 1)

            changeItem(itemId = index, picture = iconIndex)
            changeItem(itemId = quantity - index, picture = iconIndex)

            index+=2
        }

        //memoryStickList.shuffle()
    }

    suspend fun push(itemPushedId: Int) {
        currentSelectedStickId?.let {
            if (itemPushedId != it) {
                // Если иконки совпали
                if (memoryStickList[itemPushedId].pictureId == memoryStickList[it].pictureId) {
                    // Открываем обе иконки и фиксируем их
                    changeItem(itemPushedId, opened = true, enabled = false)
                    changeItem(it, opened = true, enabled = false)
                } else {
                    // Закрываем обе иконки
                    changeItem(itemPushedId, opened = false)
                    changeItem(it, opened = false)
                }
            }
        }

        // Если ни одна иконка не открыта
        if (currentSelectedStickId == null) {
            currentSelectedStickId = itemPushedId
            changeItem(itemPushedId, opened = true)

            delay(1000)
            currentSelectedStickId = null
            if (memoryStickList[itemPushedId].isEnabled) {
                changeItem(itemPushedId, opened = false)
            }
        }
    }

    fun openCloseAll(mode: Boolean) {
        for (index in 0 until memoryStickList.size) {
            memoryStickList[index] = memoryStickList[index].copy(isOpened = mode)
        }
    }

    private fun changeItem(
        itemId: Int,
        opened: Boolean? = null,
        enabled: Boolean? = null,
        picture: Int? = null
    ) {
        if (itemId < memoryStickList.size) {
            if (opened != null) {
                memoryStickList[itemId] = memoryStickList[itemId].copy(isOpened = opened)
            }
            if (enabled != null) {
                memoryStickList[itemId] = memoryStickList[itemId].copy(isEnabled = enabled)
            }
            if (picture != null) {
                memoryStickList[itemId] = memoryStickList[itemId].copy(pictureId = picture)
            }
        }
    }
}