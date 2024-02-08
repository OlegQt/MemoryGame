package com.memorygame.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.memorygame.presentation.theme.GameTheme
import com.memorygame.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen(vm: GameViewModel) {
    val darkMode = vm.darkMode.observeAsState()

    GameTheme(isDarkTheme = darkMode.value ?: false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            App(vm = vm)
        }
    }
}

@Composable
fun App(vm: GameViewModel) {

    val logTxt = vm.logLine.observeAsState()

    Column() {
        Text(text = logTxt.value.toString())
        Row(modifier = Modifier.fillMaxWidth()) {
            Switch(
                checked = vm.darkMode.observeAsState().value ?: false,
                onCheckedChange = { vm.switchTheme() })
            Button(onClick = { vm.newGame(10) }) {
                Text(text = "newSet")
            }
            Button(onClick = { }) {
                Text(text = "Button")
            }
        }

        DynamicObjects(vm = vm)
    }
}

@Composable
fun DynamicObjects(vm: GameViewModel) {
    val quantity = vm.memoryCount.observeAsState()

    Text(text = "Q=$quantity")

    Column(modifier = Modifier.fillMaxWidth()) {
        repeat(quantity.value!!) {
            MemoryStick(stickId = it, vm)
        }
    }
}

@Composable
fun MemoryStick(stickId: Int, vm: GameViewModel) {
    val stickState = vm.getStick(id = stickId).collectAsState()
    val stick = stickState.value

    Button(onClick = { vm.pushItem(itemPushedId = stick.id) }) {
        Text(text = stick.name)

    }
}