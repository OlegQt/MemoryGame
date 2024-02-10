package com.memorygame.presentation


class GameLogic() {

    private var quantityOfSticks = 0
    private val memoryStickList = mutableListOf<MemoryStick>()

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

    fun getGameState(): List<MemoryStick> {
        return memoryStickList.toList()
    }
}