package com.memorygame.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.memorygame.presentation.MemoryStick
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
    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            Switch(
                checked = vm.darkMode.observeAsState().value ?: false,
                onCheckedChange = { vm.switchTheme() })
            Button(onClick = { vm.newGame(6) }) {
                Text(text = "newSet")
            }
        }
        DataList(viewModel = vm)
    }
}

@Composable
fun DataList(viewModel: GameViewModel) {
    val dataList = viewModel.getGameState

    Column {
        dataList.chunked(4).forEach {
            Row {
                it.forEach { data ->
                    // Display the list of data using composable
                    MemoryStick(stick = data, vm = viewModel)
                }
            }
        }
    }
}


@Composable
fun MemoryStick(stick: MemoryStick, vm: GameViewModel) {

    val isOpened = stick.isOpened

    IconButton(onClick = {
        vm.pushStick(stick.id)
    }) {
        Column {
            // ICON
            if (isOpened) Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = null)
            else Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)

            // TimeLeft
            Text(text = stick.name, fontSize = 12.sp)
        }
    }
}