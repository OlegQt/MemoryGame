package com.memorygame.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GameLogic {
    private var memorySticksQuantity = 2

    val memoryList = mutableListOf<Flow<MemoryStick>>()

    fun changeSticksQuantity(newQuantity: Int) {
        this.memorySticksQuantity = newQuantity

        memoryList.clear()
        repeat(memorySticksQuantity) {
            memoryList.add(flow { emit(MemoryStick("stick $it")) })
        }
    }

}

data class MemoryStick(
    var name: String
)