package com.memorygame.presentation

data class MemoryStick(
    var id: Int = 0,
    var name: String="Stick $id",
    var isOpened: Boolean = false,
    var pictureId:Int = 0,
    var isEnabled:Boolean  = true
)