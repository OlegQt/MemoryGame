package com.memorygame.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class GameLogic {

    private var listOfSticks: MutableList<MutableStateFlow<MemoryStick>> = mutableListOf()
    val gameState = MutableStateFlow(listOfSticks)


    fun changeSticksQuantity(newQuantity: Int) {
        listOfSticks = mutableListOf()

        repeat(newQuantity) {
            listOfSticks.add(MemoryStick(name = it.toString()).toStateFlow())
        }

        gameState.update { listOfSticks }
    }

    fun update() {
        listOfSticks.forEach {
            val element = it.value
            element.name = element.name.plus(" /a")
            it.value = element
        }

        gameState.update { listOfSticks }
    }

    data class MemoryStick(
        var name: String,
        var isPushed: Boolean = false
    ) {
        fun toStateFlow(): MutableStateFlow<MemoryStick> = MutableStateFlow(this)
    }

}